package edu.uco.avalon;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityContactUs extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adp;
    Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        spinner = (Spinner) findViewById(R.id.spinner);
        adp = ArrayAdapter.createFromResource(this, R.array.contactList, android.R.layout.simple_dropdown_item_1line);
        adp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adp);
        submit();
    }

    private void submit() {
        sub = (Button) findViewById(R.id.submit);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog.Builder(ActivityContactUs.this)
                        .setTitle("Submit")
                        .setMessage("Thanks for contacting us, We will get back to you ASAP ")
                        .setPositiveButton("Go Back to Main", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(ActivityContactUs.this, ActivityMain.class));

                            }
                        })
                        .create().show();
            }
        });


    }


}
