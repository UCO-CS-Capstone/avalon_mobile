package edu.uco.avalon;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by cdcal on 2/7/2018.
 */

public class EquipmentOptionsFragment extends Fragment {

    Button createEquipmentButton, editTypesButton, editEquipmentButton;


    public EquipmentOptionsFragment(){
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_equipment_options, container, false);

        //Start of Create Equipment Button *********************************************************
        createEquipmentButton = view.findViewById(R.id.createEquipmentButton);
        createEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment addedFragment = new CreateEquipmentFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, addedFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        //End of Create Equipment Button ***********************************************************

        editTypesButton = view.findViewById(R.id.editTypesButton);
        editTypesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment addedFragment = new ManageTypesFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, addedFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        editEquipmentButton = view.findViewById(R.id.editEquipmentButton);
        editEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment addedFragment = new EditEquipmentListFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, addedFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });



        return view;
    }


}
