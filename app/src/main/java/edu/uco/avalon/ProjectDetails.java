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
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        //Get info from intent
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", -1);

        projectName = findViewById(R.id.etProjectName);
        startDate = findViewById(R.id.etStartDate);
        endDate = findViewById(R.id.etEndDate);

        if(id != -1){
            project = Project.projectList.get(id);

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
        intent.putExtra("ID", id);

        setResult(RESULT_OK, intent);
        finish();
    }

    public void createMileStone(View view){
        Intent myIntent = new Intent(this, MilestoneDetailsActivity.class);
        this.startActivity(myIntent);
    }
}
