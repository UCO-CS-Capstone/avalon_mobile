package edu.uco.avalon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MilestoneDetailsActivity extends AppCompatActivity {
    private Milestone milestone;
    private Project project;
    private int id,
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
        id = intent.getIntExtra("ID", -1);
        projectID = intent.getIntExtra("ProjectID", -1);

        projectName = findViewById(R.id.tvProjectName);
        estCost = findViewById(R.id.tvEstCost);
        startDate = findViewById(R.id.etStartDate);
        estEndDate = findViewById(R.id.etEstEndDate);
        milestoneName = findViewById(R.id.etMilesoneName);

        if(projectID != -1){
            project = Project.projectList.get(projectID);
            milestone = project.milestones.get(id);

            projectName.setText(Project.projectList.get(milestone.getProjectID()).getName());
            estCost.setText(String.valueOf(milestone.getCost()));
            startDate.setText(milestone.getStartDate());
            estEndDate.setText(milestone.getEstEndDate());
            milestoneName.setText(milestone.getMilestoneName());
        }
    }

    public void saveChanges(View view){
        milestone.setMilestoneName(milestoneName.getText().toString());
        milestone.setEstEndDate(estEndDate.getText().toString());
        milestone.setStartDate(startDate.getText().toString());

//        if()

        Intent intent = new Intent();
        intent.putExtra("ID", id);

        setResult(RESULT_OK, intent);
        finish();
    }
}
