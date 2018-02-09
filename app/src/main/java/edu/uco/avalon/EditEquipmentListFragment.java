package edu.uco.avalon;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cdcal on 2/7/2018.
 */

public class EditEquipmentListFragment extends Fragment {

    ListView equipmentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_equipment_list, container, false);

        equipmentList = view.findViewById(R.id.equipmentListView);

        //get the equipment list from the database
        String[] from = new String[] {"Name", "Type"};
        int[] to = new int[] { R.id.large_Name, R.id.small_Type};

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();

        for(int i = 0; i < Equipment.equipmentList.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Name", "" + Equipment.equipmentList.get(i).getName());
            map.put("Type", "" + Equipment.equipmentList.get(i).getType());
            fillMaps.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), fillMaps, R.layout.equipment_list_item, from, to);
        equipmentList.setAdapter(adapter);
        equipmentList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        equipmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //pass the id to the api and send the equipment info it gives.

                Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                Equipment.editOption = Equipment.equipmentList.get(i); //should get from DB
                Equipment.id = i;

                Fragment addedFragment = new EditEquipmentFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, addedFragment);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

//        ArrayAdapter<Equipment> adapter = new ArrayAdapter<>(this.getActivity(),
//                R.layout.equipment_list_item, Equipment.equipmentList);
//        equipmentList.setAdapter(adapter);
//        equipmentList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        equipmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //pass the id to the api and send the equipment info it gives.
//
//                Equipment.editOption = Equipment.equipmentList.get(i); //should get from DB
//
//                Fragment addedFragment = new EditEquipmentFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//                transaction.replace(R.id.fragment_container, addedFragment);
//                transaction.addToBackStack(null);
//
//                transaction.commit();
//
//            }
//        });


        return view;
    }


}
