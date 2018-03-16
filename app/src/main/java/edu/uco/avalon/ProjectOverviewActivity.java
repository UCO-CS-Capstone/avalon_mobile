package edu.uco.avalon;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProjectOverviewActivity extends AppCompatActivity {
//    private ArrayList<Project> projectModels = new ArrayList<>();;
    private ListView lvProjectOverview;
    private ProjectOverviewAdapter projectOverviewAdapterdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_overview);

        //For project overview
        lvProjectOverview = findViewById(R.id.projectOverviewList);

        //Test data
        //If there is already project don't add more
        if(Project.projectList.size() == 0) {
            Project.projectList.add(new Project("Oklahoma City South", "06/27/2018",
                    "07/13/2018", "", 500, "$500", 250,
                    "$250","On Schedule"));
            Project.projectList.add(new Project("Oakland", "05/27/2018",
                    "12/17/2019", "", 4000, "$4,000",500,
                    "$500","On Schedule"));
            Project.projectList.add(new Project("Dallas, TX", "01/15/2018",
                    "03/14/2018", "", 5500, "$5,500",20,
                    "$20","Behind Schedule"));
            Project.projectList.add(new Project("New York", "02/12/2019",
                    "01/23/2018", "", 999, "$999",999,
                    "$999","Current cost has reached its estimated cost."));
            Project.projectList.add(new Project("Las Vegas", "08/07/2018",
                    "07/03/2019", "", 500, "$500",2050,
                    "$2,050","Current cost exceeds estimated cost."));
            Project.projectList.add(new Project("Kansas City", "01/22/2018",
                    "5/09/2018", "03/15/2018", 30000, "$30,000",3000,
                    "$3,000","Finished"));
            Project.projectList.add(new Project("Arkham City", "01/22/2017",
                    "5/09/2018", "03/15/2018", 3000, "$3,000",3000,
                    "$3,000","Done"));
        }

//        projectModels =Project.projectList ;

        projectOverviewAdapterdapter = new ProjectOverviewAdapter(
                Project.projectList, getApplicationContext());

        lvProjectOverview.setAdapter(projectOverviewAdapterdapter);

        lvProjectOverview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvProjectOverview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case R.id.menu_delete:
                        deleteData();
                        actionMode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu_equipment_list, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are
                // deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode,
                                                  int position, long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
                actionMode.setTitle("(" + lvProjectOverview.getCheckedItemCount()
                        + " selected)");
            }
        });

        //When a project overview item is selected
        lvProjectOverview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project projectModel = Project.projectList.get(position);

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

                Project.projectList.set(position, project);
                lvProjectOverview.invalidateViews(); //Update the changes
            }
        }
    }

    private void deleteData() {

        SparseBooleanArray selected = lvProjectOverview.getCheckedItemPositions();
        for (int i = selected.size() - 1; i >= 0; i--) {
            if (selected.valueAt(i)) {
                Project.projectList.remove(selected.keyAt(i));
            }
        }
        projectOverviewAdapterdapter.notifyDataSetChanged();
    }
}
