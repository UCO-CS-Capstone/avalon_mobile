package edu.uco.avalon;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created by cdcal on 2/7/2018.
 */

public class EditEquipmentListFragment extends Fragment {

    ListView equipmentListView;
    TextView emptyText;
    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_equipment_list, container, false);

        //get the equipment list from the database
        //Start of equipmentListView ***************************************************************
        equipmentListView = view.findViewById(R.id.equipmentListView);
        String[] from = new String[] {"Name", "Type"};
        int[] to = new int[] { R.id.large_Name, R.id.small_Type};

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();

        for(int i = 0; i < Equipment.equipmentList.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Name", "" + Equipment.equipmentList.get(i).getName());
            map.put("Type", "" + Equipment.equipmentList.get(i).getType());
            fillMaps.add(map);
        }
        adapter = new SimpleAdapter(this.getActivity(), fillMaps, R.layout.equipment_list_item, from, to);
        equipmentListView.setAdapter(adapter);
        equipmentListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        equipmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //pass the id to the api and send the equipment info it gives.
                Equipment.editOption = Equipment.equipmentList.get(i); //should get from DB
                Equipment.id = i;

                Fragment addedFragment = new EditEquipmentFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, addedFragment);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });
        equipmentListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                MenuInflater inflater = getActivity().getMenuInflater();
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
                actionMode.setTitle("(" + equipmentListView.getCheckedItemCount()
                        + " selected)");
            }
        });
        //End of equipmentListView *****************************************************************

        //Start of emptyText ***********************************************************************
        emptyText = view.findViewById(R.id.list_emptyText);
        emptyText.setVisibility((adapter.isEmpty())?View.VISIBLE:View.GONE);
        //End of emptyText *************************************************************************

        return view;
    }

    private void deleteData() {

        SparseBooleanArray selected = equipmentListView.getCheckedItemPositions();
        for(int i = selected.size() - 1; i >= 0; i--){
            if (selected.valueAt(i)) {
                Equipment.equipmentList.remove(selected.keyAt(i));
            }
        }

        //if deletion was successful
        //Toast.makeText(getContext(), "Equipment Deleted Successfully.", Toast.LENGTH_SHORT).show();

        //get the equipment list from the database. Used to refresh the listview
        String[] from = new String[] {"Name", "Type"};
        int[] to = new int[] { R.id.large_Name, R.id.small_Type};
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();

        for(int i = 0; i < Equipment.equipmentList.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Name", "" + Equipment.equipmentList.get(i).getName());
            map.put("Type", "" + Equipment.equipmentList.get(i).getType());
            fillMaps.add(map);
        }
        adapter = new SimpleAdapter(this.getActivity(), fillMaps, R.layout.equipment_list_item, from, to);
        equipmentListView.setAdapter(adapter);
       // adapter.notifyDataSetChanged(); //doesn't work with SimpleAdapter
        emptyText.setVisibility((adapter.isEmpty())?View.VISIBLE:View.GONE);



        //if deletion was unsuccessful
        //Toast.makeText(getContext(), "Equipment could not be deleted....", Toast.LENGTH_SHORT);
    }


}
