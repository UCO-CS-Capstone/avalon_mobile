package edu.uco.avalon;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.graphics.pdf.PdfRenderer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ActivityReport extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    public final String TAG = "ActivityReport";

    String projectName;
    String equipmentCost;
    String maintenanceCost;
    String totalCost;
    String beginDate;
    String endDate;

    interface CurrentView {
        int OPTIONS_LAYOUT = 1;
        int READ_LAYOUT = 2;
        int WRITE_LAYOUT = 3;
        int PDF_SELECTION_LAYOUT = 4;
    }


    LinearLayout optionsLayout;
    LinearLayout readLayout;
    LinearLayout writeLayout;
    LinearLayout pdfSelectionLayout;

    private static int currentView;

    // Pdf content will be generated with User Input Text
    TextView pdfContentView;
    //For navigating back
    MenuItem closeOption;
    // List view for showing pdf files
    ListView pdfList;
    //Background task to generate pdf file listing
    PdfListLoadTask listTask;
    //Adapter to list view
    ArrayAdapter<String> adapter;
    // array of pdf files
    File[] filelist;

    //index to track currentPage in rendered Pdf
    private static int currentPage = 0;
    //View on which Pdf content will be rendered
    ImageView pdfView;

    //Currently rendered Pdf file
    String openedPdfFileName;
    Button generatePdf;
    Button next;
    Button previous;

    //File Descriptor for rendered Pdf file
    private ParcelFileDescriptor mFileDescriptor;
    //For rendering a PDF document
    private PdfRenderer mPdfRenderer;
    //For opening current page, render it, and close the page
    private PdfRenderer.Page mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        projectName = getIntent().getStringExtra("PROJNAME");
        equipmentCost = getIntent().getStringExtra("EQUIP");
        maintenanceCost = getIntent().getStringExtra("MAINT");
        totalCost = getIntent().getStringExtra("TOTAL");
        beginDate = getIntent().getStringExtra("BEGIN");
        endDate = getIntent().getStringExtra("END");

        optionsLayout = (LinearLayout) findViewById(R.id.options_layout);
        readLayout = (LinearLayout) findViewById(R.id.read_layout);
        writeLayout = (LinearLayout) findViewById(R.id.write_layout);
        pdfSelectionLayout = (LinearLayout) findViewById(R.id.pdf_selection_layout);
        pdfContentView = (TextView) findViewById(R.id.pdf_content);

        findViewById(R.id.read_pdf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateView(CurrentView.PDF_SELECTION_LAYOUT);
            }
        });
        findViewById(R.id.create_new_pdf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setPDFContent;
                updateView(CurrentView.WRITE_LAYOUT);
                setPDFContent = "\tName: " + projectName + "\n"
                        + "\tTimeline: " + beginDate + "-" + endDate + "\n"
                        + "\tEquip. Cost: $" + equipmentCost + "\n"
                        + "\tMaint. Cost: $" + maintenanceCost + "\n"
                        + "\tTotal Cost: $" + totalCost + "\n";
                pdfContentView.setText(setPDFContent);
            }
        });
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage++;
                showPage(currentPage);
            }
        });
        previous = (Button) findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage--;
                showPage(currentPage);
            }
        });
        generatePdf = (Button) findViewById(R.id.generate_pdf);
        generatePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        Log.v(TAG,"Permission is granted");
                        if (pdfContentView.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(),
                                    "Please preform another cost analysis.", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            new PdfGenerationTask().execute();
                            view.setEnabled(false);
                        }
                    } else {

                        Log.v(TAG,"Permission is revoked");
                        ActivityCompat.requestPermissions(ActivityReport.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                }
                else { //permission is automatically granted on sdk<23 upon installation
                    Log.v(TAG,"Permission is granted");
                    if (pdfContentView.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(),
                                "Please enter name to generate Report", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        new PdfGenerationTask().execute();
                        view.setEnabled(false);
                    }
                }
            }
        });

        pdfList = (ListView) findViewById(R.id.pdfList);
        pdfList.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //On Clicking list item, Render Pdf file corresponding to filePath
                openedPdfFileName = adapter.getItem(position);
                openRenderer(openedPdfFileName);
                updateView(CurrentView.READ_LAYOUT);
            }
        });
        pdfView = (ImageView) findViewById(R.id.pdfView);

        currentView = CurrentView.OPTIONS_LAYOUT;
    }

    @Override
    public void onBackPressed() {
        if (currentView == CurrentView.PDF_SELECTION_LAYOUT) {
            updateView(CurrentView.OPTIONS_LAYOUT);
            //updateActionBarText();
        } else if (currentView == CurrentView.READ_LAYOUT) {
            if (listTask != null)
                listTask.cancel(true);
            listTask = new PdfListLoadTask();
            listTask.execute();
            updateView(CurrentView.PDF_SELECTION_LAYOUT);
        } else if (currentView == CurrentView.WRITE_LAYOUT) {
            hideInputMethodIfShown();
            updateView(CurrentView.OPTIONS_LAYOUT);
        }else{
            super.onBackPressed();
        }
    }


    private void hideInputMethodIfShown() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(pdfContentView.getWindowToken(), 0, null);
    }


    private void updateView(int updateView) {
        switch (updateView) {
            case CurrentView.OPTIONS_LAYOUT:
                currentView = CurrentView.OPTIONS_LAYOUT;
                //closeOption.setVisible(false);
                optionsLayout.setVisibility(View.VISIBLE);
                readLayout.setVisibility(View.INVISIBLE);
                writeLayout.setVisibility(View.INVISIBLE);
                pdfSelectionLayout.setVisibility(View.INVISIBLE);
                break;
            case CurrentView.READ_LAYOUT:
                currentView = CurrentView.READ_LAYOUT;
                showPage(currentPage);

                //closeOption.setVisible(true);
                optionsLayout.setVisibility(View.INVISIBLE);
                readLayout.setVisibility(View.VISIBLE);
                writeLayout.setVisibility(View.INVISIBLE);
                pdfSelectionLayout.setVisibility(View.INVISIBLE);
                break;
            case CurrentView.PDF_SELECTION_LAYOUT:
                currentView = CurrentView.PDF_SELECTION_LAYOUT;

                closeRenderer();

                if (listTask != null)
                    listTask.cancel(true);
                listTask = new PdfListLoadTask();
                listTask.execute();

                //closeOption.setVisible(true);
                optionsLayout.setVisibility(View.INVISIBLE);
                readLayout.setVisibility(View.INVISIBLE);
                writeLayout.setVisibility(View.INVISIBLE);
                pdfSelectionLayout.setVisibility(View.VISIBLE);
                break;
            case CurrentView.WRITE_LAYOUT:
                currentView = CurrentView.WRITE_LAYOUT;

                //closeOption.setVisible(true);
                optionsLayout.setVisibility(View.INVISIBLE);
                readLayout.setVisibility(View.INVISIBLE);
                writeLayout.setVisibility(View.VISIBLE);
                pdfSelectionLayout.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void openRenderer(String filePath) {
        File file = new File(filePath);
        try {
            mFileDescriptor = ParcelFileDescriptor.open(file,
                    ParcelFileDescriptor.MODE_READ_ONLY);
            mPdfRenderer = new PdfRenderer(mFileDescriptor);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeRenderer() {

        try {
            if (mCurrentPage != null)
                mCurrentPage.close();
            if (mPdfRenderer != null)
                mPdfRenderer.close();
            if (mFileDescriptor != null)
                mFileDescriptor.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void showPage(int index) {
        if (mPdfRenderer == null || mPdfRenderer.getPageCount() <= index
                || index < 0) {
            return;
        }
        // For closing the current page before opening another one.
        try {
            if (mCurrentPage != null) {
                mCurrentPage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Open page with specified index
        mCurrentPage = mPdfRenderer.openPage(index);
        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(),
                mCurrentPage.getHeight(), Bitmap.Config.ARGB_8888);

        //Pdf page is rendered on Bitmap
        mCurrentPage.render(bitmap, null, null,
                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        //Set rendered bitmap to ImageView
        pdfView.setImageBitmap(bitmap);
    }


    private class PdfListLoadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String filePath = Environment.getExternalStorageDirectory().toString();
            File files = new File(filePath);
            filelist = files.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return ((name.endsWith(".pdf")));
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub

            if (filelist != null && filelist.length >= 1) {
                ArrayList<String> fileNameList = new ArrayList<>();
                for (int i = 0; i < filelist.length; i++)
                    fileNameList.add(filelist[i].getPath());
                adapter = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.report_list, fileNameList);
                pdfList.setAdapter(adapter);
            } else {
                Toast.makeText(getApplicationContext(),
                        "No Report found, Please create new Report",
                        Toast.LENGTH_LONG).show();
                updateView(CurrentView.OPTIONS_LAYOUT);
            }
        }

    }


    private class PdfGenerationTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            PdfDocument document = new PdfDocument();

            // repaint the user's text into the page
            View content = findViewById(R.id.pdf_content);

            // crate a page description
            int pageNumber = 1;
            PageInfo pageInfo = new PageInfo.Builder(content.getWidth(),
                    content.getHeight() - 20, pageNumber).create();

            // create a new page from the PageInfo
            PdfDocument.Page page = document.startPage(pageInfo);

            content.draw(page.getCanvas());

            // do final processing of the page
            document.finishPage(page);

            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
            String pdfName = "ProjectReport"
                    + sdf.format(Calendar.getInstance().getTime()) + ".pdf";

            File outputFile = new File(Environment.getExternalStorageDirectory(), pdfName);

            try {
                outputFile.createNewFile();
                OutputStream out = new FileOutputStream(outputFile);
                document.writeTo(out);
                document.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return outputFile.getPath();
        }

        @Override
        protected void onPostExecute(String filePath) {
            if (filePath != null) {
                generatePdf.setEnabled(true);
                pdfContentView.setText("");
                Toast.makeText(getApplicationContext(),
                        "Report saved at " + filePath, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Error in Report creation" + filePath, Toast.LENGTH_SHORT)
                        .show();
            }

        }

    }

}
