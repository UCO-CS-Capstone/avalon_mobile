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

public class ManageTypesFragment extends Fragment {

    EditText typeEdit;
    Spinner typeSpinner;

    Button addButton;
    Button deleteButton;

    String type;
    ArrayAdapter<String> adapter;
    int typePos;


    public ManageTypesFragment() {
        type = "";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_types, container, false);

        typeEdit = view.findViewById(R.id.typeNameEdit);

        //Start of Type Spinner ********************************************************************
        typeSpinner =  view.findViewById(R.id.typeSpinner);
        adapter = new ArrayAdapter<>(this.getActivity(),
                R.layout.spinner_equipment_type, Equipment.typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                type = parent.getItemAtPosition(pos).toString();
                typePos = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type = Equipment.typeList.get(0);
                typePos = 0;
            }
        });
        //End of Type Spinner **********************************************************************

        //Start of Add Button **********************************************************************
        addButton = view.findViewById(R.id.addTypeButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typeName = typeEdit.getText().toString().trim();
                if(!typeName.equals("")) {
                    updateData(typeName); //update to db
                }
                else{
                    Toast.makeText(getContext(), "Please enter a name for this new type.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //End of Add Button ************************************************************************


        //Start of Delete Button *******************************************************************
        deleteButton = view.findViewById(R.id.deleteTypeButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
        //End of Delete Button *********************************************************************


        return view;
    }

    protected void updateData(String typeName){

        if(Equipment.typeList.contains(typeName)){
            Toast.makeText(getContext(), "This type already exists.", Toast.LENGTH_SHORT).show();
        }
        else{
            Equipment.typeList.add(typeName); //db

            Toast.makeText(getContext(), "Saved Successfully.", Toast.LENGTH_SHORT).show();
            typeSpinner.setSelection(adapter.getPosition(typeName));
            typeEdit.setText("");

        }

    }

    protected void deleteData(){

        if(Equipment.typeList.size() > 1) {
            Equipment.typeList.remove(typePos); //db

            Toast.makeText(getContext(), "Deleted Successfully.", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(getContext(), "Cannot delete the single remaining type in the list.", Toast.LENGTH_SHORT).show();
        }
    }

}
