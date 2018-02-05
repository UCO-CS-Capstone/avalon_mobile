package edu.uco.avalon;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ProjectSelection extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //For project overview
    ArrayList<Project> projectModels;
    ListView lvProjectOverview;
    private static ProjectOverviewAdapter projectOverviewAdapterdapter;
    ///////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //For project overview
        lvProjectOverview= findViewById(R.id.projectOverviewList);

        projectModels = new ArrayList<>();
        projectModels.add(new Project("Oklahoma City South","02/27/2018",
                "07/03/2018", "On Schedule"));
        projectModels.add(new Project("Oakland","05/27/2018",
                "12/17/2019", "Equipment conflict"));
        projectModels.add(new Project("Dallas, TX","01/15/2018",
                "06/14/2018", "Behind Schedule"));
        projectModels.add(new Project("New York","02/12/2018",
                "01/23/2019", "On Schedule"));

        projectOverviewAdapterdapter= new ProjectOverviewAdapter(
                projectModels,getApplicationContext());

        lvProjectOverview.setAdapter(projectOverviewAdapterdapter);

        //When a project overview item is selected
        lvProjectOverview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project projectModel = projectModels.get(position);

                Snackbar.make(view, projectModel.getName()+"\n"+"Start Date: " +
                        projectModel.getStartDate() + "\n" + "End Date: " +
                        projectModel.getEstEndDate(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
        /////////////////////////

        //TODO - Make a new project with this button?
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Create a new project?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //TODO - Change from default drawer to project selection
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //TODO - Do we need the options menu at all?
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.project_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
