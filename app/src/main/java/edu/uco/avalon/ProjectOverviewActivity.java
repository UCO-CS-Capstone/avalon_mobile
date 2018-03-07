package edu.uco.avalon;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProjectOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_overview);

        final ArrayList<Project> projectModels = Project.projectList;
        ListView lvProjectOverview;
        ProjectOverviewAdapter projectOverviewAdapter;
        ArrayList<Project> projectList = Project.projectList;

        //For project overview
        lvProjectOverview = findViewById(R.id.projectOverviewList);

        projectOverviewAdapter = new ProjectOverviewAdapter(
                projectList, getApplicationContext());

        lvProjectOverview.setAdapter(projectOverviewAdapter);

        //When a project overview item is selected
        lvProjectOverview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project projectModel = projectModels.get(position);

                Snackbar.make(view, projectModel.getName() + "\n" + "Start Date: " +
                        projectModel.getStartDate() + "\n" + "Estimated End Date: " +
                        projectModel.getEstEndDate() + "\n" + "Actual End Date: " +
                        projectModel.getActualEndDate() + "\n" + "Estimated Overall Cost: " +
                        projectModel.getEstCost() + "\n" + "Current Cost: " +
                        projectModel.getCurrentCost(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

    //Michael's version before project changes
/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_overview);

        final ArrayList<Project> projectModels;
        ListView lvProjectOverview;
        ProjectOverviewAdapter projectOverviewAdapterdapter;

        //For project overview
        lvProjectOverview = findViewById(R.id.projectOverviewList);

        projectModels = new ArrayList<>();
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
                Project.projectList, getApplicationContext());

        lvProjectOverview.setAdapter(projectOverviewAdapterdapter);

        //When a project overview item is selected
        lvProjectOverview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project projectModel = projectModels.get(position);

                Snackbar.make(view, projectModel.getName() + "\n" + "Start Date: " +
                        projectModel.getStartDate() + "\n" + "End Date: " +
                        projectModel.getEstEndDate(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }*/
}
