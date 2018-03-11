package edu.uco.avalon;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProjectOverviewActivity extends AppCompatActivity {
    private final ArrayList<Project> projectModels = new ArrayList<>();;
    private ListView lvProjectOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_overview);
        ProjectOverviewAdapter projectOverviewAdapterdapter;

        //For project overview
        lvProjectOverview = findViewById(R.id.projectOverviewList);

        projectModels.add(new Project("Oklahoma City South", "02/27/2018",
                "07/03/2018", "On Schedule"));
        projectModels.add(new Project("Oakland", "05/27/2018",
                "12/17/2019", "Equipment conflict"));
        projectModels.add(new Project("Dallas, TX", "01/15/2018",
                "06/14/2018", "Behind Schedule"));
        projectModels.add(new Project("New York", "02/12/2018",
                "01/23/2019", "On Schedule"));
        projectModels.add(new Project("Las Vegas", "03/07/2018",
                "07/03/2019", "On Schedule"));
        projectModels.add(new Project("Kansas City", "01/22/2018",
                "5/09/2018", "Doomed"));
        projectModels.add(new Project("Norman", "06/15/2018",
                "06/14/2019", "Not Schedule"));
        projectModels.add(new Project("Hell, Colorado", "01/13/2018",
                "10/07/2019", "Frozen Over"));

        projectOverviewAdapterdapter = new ProjectOverviewAdapter(
                projectModels, getApplicationContext());

        lvProjectOverview.setAdapter(projectOverviewAdapterdapter);

        //When a project overview item is selected
        lvProjectOverview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project projectModel = projectModels.get(position);

                gotoProjectDetails(projectModel, position);

                Snackbar.make(view, projectModel.getName() + "\n" + "Start Date: " +
                        projectModel.getStartDate() + "\n" + "End Date: " +
                        projectModel.getEstEndDate(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

    public void gotoProjectDetails(Project projectModel, int position) {
        Intent intent = new Intent(this, ProjectDetails.class);
        intent.putExtra("Project", projectModel);
        intent.putExtra("Position", position);

        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Project project = (Project) intent.getSerializableExtra("Project");
                int position = intent.getIntExtra("Position", -1);

                projectModels.set(position, project);
                lvProjectOverview.invalidateViews(); //Update the changes

                Toast.makeText(getApplicationContext(), project.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
