package edu.uco.avalon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ProjectDetails extends AppCompatActivity {
    private EditText projectName,
                     startDate,
                     endDate;
    private Project project;
    private int projectID;

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

        if(projectID != -1){
            project = Project.projectList.get(projectID);

            projectName.setText(project.getName());
            startDate.setText(project.getStartDate());
            endDate.setText(project.getEstEndDate());
        }
    }

    public void saveChanges(View view){
        project.setName(projectName.getText().toString());
        project.setEstEndDate(endDate.getText().toString());
        project.setStartDate(startDate.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("ProjectID", projectID);

        setResult(RESULT_OK, intent);
        finish();
    }

    public void createMileStone(View view){
        Intent intent = new Intent(this, MilestoneDetailsActivity.class);
        intent.putExtra("ProjectID", projectID);
        intent.putExtra("MilestoneID", -1); //No id yet

        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                //Update the changes
            }
        }
    }
}
