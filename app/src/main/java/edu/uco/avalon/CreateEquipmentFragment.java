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
    Button deleteButton; //for child EditEquipmentFragment
    Button maintenance ;// for child EditEquipmentFragment

    String name;
    String type;

    public CreateEquipmentFragment() {
        //if creating new equipment, just get the types and nothing else.
        name = "";
        type = "";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_equipment, container, false);

        //Start of Projects Name EditText ***********************************************************
        nameEdit = view.findViewById(R.id.projectNameEdit);
        nameEdit.setText(name);
        //End of Projects Name EditText *************************************************************

        //Start of Type Spinner ********************************************************************
        typeSpinner =  view.findViewById(R.id.typeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),
                R.layout.spinner_equipment_type, Equipment.typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        if(!type.equals("")) { //for editing
            int pos = adapter.getPosition(type);
            if(pos != -1){
                typeSpinner.setSelection(pos);
            }
            else{
                typeSpinner.setSelection(0);
                Toast.makeText(getContext(), "Type could not be found. It may have been removed.", Toast.LENGTH_SHORT).show();
            }
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
        saveButton = view.findViewById(R.id.saveProjectButton);
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


        //Start of Delete Button *******************************************************************
        if(this instanceof EditEquipmentFragment){ //if in edit-mode, add the delete button stuff.
            deleteButton = view.findViewById(R.id.deleteEquipmentButton);
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteData(); //update to db
                }
            });
            maintenance = view.findViewById(R.id.btnmaintenance);
            maintenance.setVisibility(View.VISIBLE);
            maintenance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    maintenance(); //update to db
                }
            });
        }
        //End of Delete Button *********************************************************************

        return view;
    }

    protected void updateData(){

        Equipment.equipmentList.add(new Equipment(name, type, false)); //db

        Toast.makeText(getContext(), "Saved Successfully.", Toast.LENGTH_SHORT).show();

        //go back to previous screen
        getFragmentManager().popBackStack();

    }

    protected void deleteData(){

    }
    protected void maintenance(){

    }

}
