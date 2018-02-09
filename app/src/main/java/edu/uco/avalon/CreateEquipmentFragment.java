package edu.uco.avalon;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateEquipmentFragment extends Fragment {

    EditText nameEdit;
    Spinner typeSpinner;

    Button saveButton;

    String name;
    String type;

    //Dummy values to be replaced by accessing the Database
    //String typeList[] = {"Type 1", "Type 2", "Type 3", "Type 4"}; //should be accessed from db
    //static boolean created = false;


    public CreateEquipmentFragment() {
        // Required empty public constructor


        //if creating new equipment, just get the types and nothing else.
        name = "";
        type = "";
//        if(created == false) {
//            Equipment.typeList.add("Type 1");
//            Equipment.typeList.add("Type 2");
//            Equipment.typeList.add("Type 3");
//            Equipment.typeList.add("Type 4");
//            created = true;
//        }

        //if editing equipment, get that data and store into all the edit texts and such
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_equipment, container, false);

        //Start of Project Name EditText ***********************************************************
        nameEdit = view.findViewById(R.id.equipmentNameEdit);
        nameEdit.setText(name);
        //End of Project Name EditText *************************************************************

        //Start of Type Spinner ********************************************************************
        typeSpinner =  view.findViewById(R.id.typeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),
                R.layout.spinner_equipment_type, Equipment.typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        if(!type.equals("")) {
            typeSpinner.setSelection(adapter.getPosition(type)); //for editing
        }
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                type = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type = Equipment.typeList.get(0);
            }
        });
        //End of Type Spinner **********************************************************************


        //Start of Save Button *********************************************************************
        saveButton = view.findViewById(R.id.saveEquipmentButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEdit.getText().toString().trim();
                if(!name.equals("")) {
                    type = typeSpinner.getSelectedItem().toString();

                    updateData(); //update to db
                }
                else{
                    Toast.makeText(getContext(), "Please name the equipment!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //End of Save Button ***********************************************************************



        return view;
    }

    protected void updateData(){

        Equipment.equipmentList.add(new Equipment(name, type, false)); //db

        //if saving was successful
        Toast.makeText(getContext(), "Saved Successfully.", Toast.LENGTH_SHORT).show();

        //if saving was unsuccessful, don't go back to previous screen.
        //Toast.makeText(getContext(), "Saved Unsuccessfully....", Toast.LENGTH_SHORT);

        //go back to previous screen
        getFragmentManager().popBackStack();

    }

}
