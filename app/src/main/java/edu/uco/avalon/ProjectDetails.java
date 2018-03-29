package edu.uco.avalon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ProjectDetails extends AppCompatActivity {
    private EditText projectName,
            startDate,
            endDate;
    private Project project;
    private ListView lvMilestones;
    private int projectID;

    private MilestoneOverviewAdapter milestoneOverviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        //Get info from intent
        Intent intent = getIntent();
        projectID = intent.getIntExtra("ProjectID", -1);

        projectName = findViewById(R.id.etProjectName);
        startDate = findViewById(R.id.etStartDate);
        endDate = findViewById(R.id.etEndDate);

        if (projectID != -1) {
            project = Project.projectList.get(projectID);

            projectName.setText(project.getName());
            startDate.setText(project.getStartDate());
            endDate.setText(project.getEstEndDate());
        }

        lvMilestones = findViewById(R.id.lvMilestones);
        milestoneOverviewAdapter = new MilestoneOverviewAdapter(
                Project.projectList.get(projectID).milestones, getApplicationContext());
        lvMilestones.setAdapter(milestoneOverviewAdapter);
        lvMilestones.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvMilestones.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case R.id.menu_delete:
                        deleteData();
                        actionMode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu_equipment_list, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are
                // deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode,
                                                  int position, long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
                actionMode.setTitle("(" + lvMilestones.getCheckedItemCount()
                        + " selected)");
            }
        });

        //When a project overview item is selected
        lvMilestones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoMilestoneDetails(position);
            }
        });
    }

    public void gotoMilestoneDetails(int position) {
        Intent intent = new Intent(this, MilestoneDetailsActivity.class);
        intent.putExtra("ProjectID", projectID);
        intent.putExtra("MilestoneID", position);

        startActivityForResult(intent, 1);
    }

    private void deleteData() {

        SparseBooleanArray selected = lvMilestones.getCheckedItemPositions();
        for (int i = selected.size() - 1; i >= 0; i--) {
            if (selected.valueAt(i)) {
                //Remove the milestones that were selected
                Project.projectList.get(projectID).milestones.remove(selected.keyAt(i));
            }
        }

        //Check to see if there are any milestones
        if(project.milestones.size() == 0){
            project.setCurrentMilestone("");
        }

        milestoneOverviewAdapter.notifyDataSetChanged();
    }

    public void saveChanges(View view) {
        //Update the information before returning
        project.setName(projectName.getText().toString());
        project.setEstEndDate(endDate.getText().toString());
        project.setStartDate(startDate.getText().toString());

        calculateCost();

        setResult(RESULT_OK);
        finish();
    }

    public void createMileStone(View view) {
        Intent intent = new Intent(this, MilestoneDetailsActivity.class);
        intent.putExtra("ProjectID", projectID);
        intent.putExtra("MilestoneID", -1); //No id yet

        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                lvMilestones.invalidateViews(); //Update the changes
            }
        }
    }

    private void calculateCost() {
        ArrayList<Milestone> currentMilestone = project.milestones;
        double allMilestoneCosts = 0;

        for (Milestone m: currentMilestone) {
            allMilestoneCosts += m.getCost();
        }

        project.setCurrentCost(allMilestoneCosts);
    }
}
