package edu.uco.avalon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        //Set the first milestone in the array as the current
        project.setCurrentMilestone(project.milestones.get(0).getMilestoneName());

        setResult(RESULT_OK);
        finish();
    }
}
