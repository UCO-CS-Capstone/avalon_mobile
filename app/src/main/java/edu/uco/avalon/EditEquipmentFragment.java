package edu.uco.avalon;

import android.widget.Toast;

/**
 * Created by cdcal on 2/7/2018.
 */

public class EditEquipmentFragment extends CreateEquipmentFragment {

    public EditEquipmentFragment(){

        name = Equipment.editOption.getName();
        type = Equipment.editOption.getType();
    }

    @Override
    protected void updateData(){

        Equipment.equipmentList.set(Equipment.id, new Equipment(name, type, false)); //db

        //if saving was successful
        Toast.makeText(getContext(), "Saved Successfully.", Toast.LENGTH_SHORT).show();

        //if saving was unsuccessful, don't go back to previous screen.
        //Toast.makeText(getContext(), "Saved Unsuccessfully....", Toast.LENGTH_SHORT);

        //go back to previous screen
        getFragmentManager().popBackStack();
    }

    @Override
    protected void deleteData(){

        Equipment.equipmentList.remove(Equipment.id);

        //if deletion was successful
        Toast.makeText(getContext(), "Equipment Deleted Successfully.", Toast.LENGTH_SHORT).show();


        //if deletion was unsuccessful
        //Toast.makeText(getContext(), "Equipment could not be deleted....", Toast.LENGTH_SHORT);

        //go back to previous screen
        getFragmentManager().popBackStack();
    }
}
