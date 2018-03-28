package edu.uco.avalon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProjectStatusActivity extends AppCompatActivity {

    TextView projectNameText, projectStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_status);

        projectNameText = findViewById(R.id.textEquipmentName);
        projectStatusText = findViewById(R.id.textProjectStatus);

        final int position = getIntent().getIntExtra("Position", -1);

        projectNameText.setText(Project.projectList.get(position).getName());
        projectStatusText.setText(Project.projectList.get(position).getStatus());
    }
}
