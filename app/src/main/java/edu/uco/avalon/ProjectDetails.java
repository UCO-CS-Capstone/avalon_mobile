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
    private int position; //Keeps track of the project location in the array until database is used

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        //Get info from intent
        Intent intent = getIntent();
        project = (Project) intent.getSerializableExtra("Project");
        position = intent.getIntExtra("Position", -1);

        projectName = findViewById(R.id.etProjectName);
        startDate = findViewById(R.id.etStartDate);
        endDate = findViewById(R.id.etEndDate);

        if(project != null){
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
        intent.putExtra("Project", project);
        intent.putExtra("Position", position);

        setResult(RESULT_OK, intent);
        finish();
    }
}
