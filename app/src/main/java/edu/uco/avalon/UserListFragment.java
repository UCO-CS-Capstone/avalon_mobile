package edu.uco.avalon;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class UserListFragment extends Fragment {

    ListView userListView;
    TextView emptyText;
    SimpleAdapter adapter;
    Button add;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        //get the equipment list from the database
        //Start of equipmentListView ***************************************************************
        userListView = view.findViewById(R.id.userListView);
        String[] from = new String[] {"Name", "EmailandFlag"};
        int[] to = new int[] { R.id.large_Name, R.id.small_Type};


        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();

        for(int i = 0; i < UserMgmt.userlist.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Name", "" + UserMgmt.userlist.get(i).getName());
            map.put("EmailandFlag", "" + UserMgmt.userlist.get(i).getEmailandFlag());
            fillMaps.add(map);

        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Name", "" + "Add a User");
        map.put("EmailandFlag", "");
        fillMaps.add(map);
        adapter = new SimpleAdapter(this.getActivity(), fillMaps, R.layout.maintenance_list_item, from, to);
        userListView.setAdapter(adapter);
        userListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
       userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if(i >= UserMgmt.userlist.size()) {
                    //pass the id to the api and send the equipment info it gives.
                    // Equipment.editOption = Equipment.equipmentList.get(i); //should get from DB
                    //Equipment.id = i;

                    Fragment addedFragment = new AddUserFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, addedFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }
                else{
                    UserMgmt.editOption = UserMgmt.userlist.get(i); //should get from DB

                    UserMgmt.id = i;

                    Fragment addedFragment = new EditUserFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, addedFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }

            }
        });

        //End of equipmentListView *****************************************************************

        //Start of emptyText ***********************************************************************
        emptyText = view.findViewById(R.id.listuser_emptyText);
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
