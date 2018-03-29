package edu.uco.avalon;

import android.content.Intent;
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

public class ProjectOverviewActivity extends AppCompatActivity {
    private ListView lvProjectOverview;
    private ProjectOverviewAdapter projectOverviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_overview);

        //For project overview
        lvProjectOverview = findViewById(R.id.projectOverviewList);
        projectOverviewAdapter = new ProjectOverviewAdapter(
                Project.projectList, getApplicationContext());
        lvProjectOverview.setAdapter(projectOverviewAdapter);
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
            }
        });
    }

    public void gotoProjectDetails(Project projectModel, int position) {
        Intent intent = new Intent(this, ProjectDetails.class);
        intent.putExtra("ProjectID", position);

        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
            lvProjectOverview.invalidateViews(); //Update the changes
    }

    private void deleteData() {

        SparseBooleanArray selected = lvProjectOverview.getCheckedItemPositions();
        for (int i = selected.size() - 1; i >= 0; i--) {
            if (selected.valueAt(i)) {
                Project.projectList.remove(selected.keyAt(i));
            }
        }
        projectOverviewAdapter.notifyDataSetChanged();
    }
}
