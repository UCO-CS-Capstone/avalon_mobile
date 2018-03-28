package edu.uco.avalon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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

        if(projectID != -1){
            project = Project.projectList.get(projectID);

            //Check to see if its a new milestone before pulling info
            if(milestoneID != -1) {
                milestone = project.milestones.get(milestoneID);
                estCost.setText(String.valueOf(milestone.getCost()));
                startDate.setText(milestone.getStartDate());
                estEndDate.setText(milestone.getEstEndDate());
                milestoneName.setText(milestone.getMilestoneName());
            }
            else{
                milestone = new Milestone();
            }

            projectName.setText(Project.projectList.get(projectID).getName());
        }

        //Equipment list view
        lvMilestoneEquipment = findViewById(R.id.lvSelectedEquipment);
        equipmentOverviewAdapter = new EquipmentOverviewAdapter(milestone.milestoneEquipmentList,
                getApplicationContext());
        lvMilestoneEquipment.setAdapter(equipmentOverviewAdapter);
        lvMilestoneEquipment.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        //Equipment spinner
        Spinner equipmentSpinner = findViewById(R.id.spinnerEquipment);
        ArrayAdapter<Equipment> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Equipment.equipmentList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        equipmentSpinner.setAdapter(adapter);
        equipmentSpinner.setSelection(0,false);
        equipmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Toast.makeText(getApplicationContext(), Equipment.equipmentList.get(pos).getName(),
                        Toast.LENGTH_SHORT).show();

                //Add the selected equipment to the equipment list
                milestone.milestoneEquipmentList.add(Equipment.equipmentList.get(pos));
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
        milestone.milestoneEquipmentList.clear();
        super.onBackPressed();
    }

    public void saveChanges(View view){
        milestone.setMilestoneName(milestoneName.getText().toString());
        milestone.setEstEndDate(estEndDate.getText().toString());
        milestone.setStartDate(startDate.getText().toString());
        milestone.setMilestoneName(milestoneName.getText().toString());
        milestone.generateCosts();

        //If a new milestone add to the array list
        if(milestoneID == -1){
            Project.projectList.get(projectID).milestones.add(milestone);
            milestone.setId(Project.projectList.get(projectID).milestones.size()-1);
        }
        else{
            //Update milestone with new values
            Project.projectList.get(projectID).milestones.set(milestoneID, milestone);
        }

        //Set the first milestone in the array as the current
        project.setCurrentMilestone(project.milestones.get(0).getMilestoneName());

        setResult(RESULT_OK);
        finish();
    }
}
