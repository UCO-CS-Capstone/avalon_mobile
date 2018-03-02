package edu.uco.avalon;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cdcal on 2/7/2018.
 */

public class MaintenancetListFragment extends Fragment {

    ListView maintenanceListView;
    TextView emptyText;
    SimpleAdapter adapter;
    Button add;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maintenance_list, container, false);

        //get the equipment list from the database
        //Start of equipmentListView ***************************************************************
        maintenanceListView = view.findViewById(R.id.maintenanceListView);
        String[] from = new String[] {"Date", "Type"};
        int[] to = new int[] { R.id.large_Name, R.id.small_Type};

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        Equipment temp = (Equipment.equipmentList.get(Maintenance.selected));

        for(int i = 0; i < temp.maintenanceList.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Date", "" + temp.maintenanceList.get(i).getDate());
            map.put("Type", "" + temp.maintenanceList.get(i).getType());
            fillMaps.add(map);

        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Date", "" + "Add a Maintenance Date");
        map.put("Type", "");
        fillMaps.add(map);
        adapter = new SimpleAdapter(this.getActivity(), fillMaps, R.layout.maintenance_list_item, from, to);
        maintenanceListView.setAdapter(adapter);
        maintenanceListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        maintenanceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if(i >= Equipment.equipmentList.get(Maintenance.selected).maintenanceList.size()) {
                    //pass the id to the api and send the equipment info it gives.
                    // Equipment.editOption = Equipment.equipmentList.get(i); //should get from DB
                    //Equipment.id = i;

                    Fragment addedFragment = new MaintenanceFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, addedFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }

            }
        });

        //End of equipmentListView *****************************************************************

        //Start of emptyText ***********************************************************************
        emptyText = view.findViewById(R.id.list_emptyText);
        emptyText.setVisibility((adapter.isEmpty())?View.VISIBLE:View.GONE);
        //End of emptyText *************************************************************************

        /*add = (Button) view.findViewById(R.id.btnAddMaintenance);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment addedFragment = new MaintenanceFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, addedFragment);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });
        */return view;
    }




}
