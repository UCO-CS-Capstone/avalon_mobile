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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MilestoneDetailsActivity extends AppCompatActivity {
    private Milestone milestone;
    private Project project;
    private int milestoneID,
            projectID;

    private TextView projectName,
            estCost;
    private EditText startDate,
            estEndDate,
            milestoneName;
    private ListView lvMilestoneEquipment;

    private EquipmentOverviewAdapter equipmentOverviewAdapter;

    //Store the selected equipment until saved
    private ArrayList<Equipment> tempEquipment = new ArrayList<>();

    //To keep track of which equipment was added to a project for activeCount.
    private ArrayList<Integer> equipmentCount = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone);

        //Get info from intent
        Intent intent = getIntent();
        milestoneID = intent.getIntExtra("MilestoneID", -1);
        projectID = intent.getIntExtra("ProjectID", -1);

        projectName = findViewById(R.id.tvProjectName);
        estCost = findViewById(R.id.tvEstCost);
        startDate = findViewById(R.id.etStartDate);
        estEndDate = findViewById(R.id.etEstEndDate);
        milestoneName = findViewById(R.id.etMilesoneName);

        if (projectID != -1) {
            project = Project.projectList.get(projectID);

            //Check to see if its a new milestone before pulling info
            if (milestoneID != -1) {
                milestone = project.milestones.get(milestoneID);
                estCost.setText(String.valueOf(milestone.getCost()));
                startDate.setText(milestone.getStartDate());
                estEndDate.setText(milestone.getEstEndDate());
                milestoneName.setText(milestone.getMilestoneName());

            } else {
                milestone = new Milestone();

            }

            projectName.setText(Project.projectList.get(projectID).getName());

            //Copy equipment into the temp list
            tempEquipment = new ArrayList<>(milestone.milestoneEquipmentList);


            //Check if equipment is used in other projects
        }

        //Equipment list view
        lvMilestoneEquipment = findViewById(R.id.lvSelectedEquipment);
        if(milestoneID != -1){
            equipmentOverviewAdapter = new EquipmentOverviewAdapter(tempEquipment,
                    getApplicationContext(), milestoneID, projectID);
        }
        else{
            equipmentOverviewAdapter = new EquipmentOverviewAdapter(tempEquipment,
                    getApplicationContext(), milestoneID, projectID, true);
        }

        lvMilestoneEquipment.setAdapter(equipmentOverviewAdapter);
        lvMilestoneEquipment.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvMilestoneEquipment.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
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
                actionMode.setTitle("(" + lvMilestoneEquipment.getCheckedItemCount()
                        + " selected)");
            }
        });

        //Equipment spinner
        Spinner equipmentSpinner = findViewById(R.id.spinnerEquipment);
        ArrayAdapter<Equipment> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Equipment.equipmentList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        equipmentSpinner.setAdapter(adapter);
        equipmentSpinner.setSelection(0, false);
        equipmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                //Do not select the blank item in the list
                if(id == 0)
                    return;

                //Add the selected equipment to the temporary equipment list
                tempEquipment.add(Equipment.equipmentList.get(pos));
                Equipment.equipmentList.get(pos).increaseActiveCount(); //Flags that the equipment is added to a project.
                equipmentCount.add(pos);
                equipmentOverviewAdapter.setStrings(startDate.getText().toString(), estEndDate.getText().toString());
                lvMilestoneEquipment.invalidateViews(); //Update the changes
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        //Don't save the changes
        tempEquipment.clear();
        for(int i = 0; i < equipmentCount.size(); i++){ //subtract count of equipment for a project since it's cancelled.
            Equipment.equipmentList.get(equipmentCount.get(i)).decreaseActiveCount();
        }
        equipmentCount.clear();
        super.onBackPressed();
    }

    public void saveChanges(View view) {
        milestone.setMilestoneName(milestoneName.getText().toString());
        milestone.setEstEndDate(estEndDate.getText().toString());
        milestone.setStartDate(startDate.getText().toString());
        milestone.setMilestoneName(milestoneName.getText().toString());

        //Update the cost per day
        calculateCost();

        //If a new milestone add to the array list
        if (milestoneID == -1) {
            Project.projectList.get(projectID).milestones.add(milestone);
            milestone.setId(Project.projectList.get(projectID).milestones.size() - 1);
        } else {
            //Update milestone with new values
            Project.projectList.get(projectID).milestones.set(milestoneID, milestone);
        }

        milestone.milestoneEquipmentList = new ArrayList<>(tempEquipment);

        //Set the first milestone in the array as the current
        project.setCurrentMilestone(project.milestones.get(0).getMilestoneName());

        setResult(RESULT_OK);
        finish();
    }

    private void deleteData() {
        SparseBooleanArray selected = lvMilestoneEquipment.getCheckedItemPositions();
        for (int i = selected.size() - 1; i >= 0; i--) {
            if (selected.valueAt(i)) {
                try {
                    //to keep track of how many projects this equipment is on.
                    tempEquipment.get(selected.keyAt(i)).decreaseActiveCount();
                    tempEquipment.remove(selected.keyAt(i));
                } catch (Exception e) {
                    Log.e("MileStoneDetails", e.toString());
                }
            }
        }
        equipmentOverviewAdapter.notifyDataSetChanged();
    }

    private void calculateCost() {
        ArrayList<Equipment> currentEquipment = tempEquipment;
        DateFormat shortDf = DateFormat.getDateInstance(DateFormat.SHORT);
        long days = 0;

        try {
            Date start = shortDf.parse(milestone.getStartDate());
            Date end = shortDf.parse(milestone.getEstEndDate());

            long diff = end.getTime() - start.getTime();

            //Add one to count for the first day used
            days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;

        } catch (ParseException exception) {
            Log.e("ProjectDetails", "calculateCost " + exception);
        }

        double allEquipmentCosts = 0;
        for (Equipment e: currentEquipment) {
            allEquipmentCosts += (days * e.getDaileyCost());
        }

        milestone.setCost(allEquipmentCosts);
    }
}
