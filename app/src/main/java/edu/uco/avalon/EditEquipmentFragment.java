package edu.uco.avalon;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

        Toast.makeText(getContext(), "Saved Successfully.", Toast.LENGTH_SHORT).show();

        //go back to previous screen
        getFragmentManager().popBackStack();
    }

    @Override
    protected void deleteData(){

        Equipment.equipmentList.remove(Equipment.id);

        Toast.makeText(getContext(), "Equipment Deleted Successfully.", Toast.LENGTH_SHORT).show();


        //go back to previous screen
        getFragmentManager().popBackStack();
    }

    protected  void maintenance(){

        Fragment addedFragment = new MaintenancetListFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, addedFragment);
        transaction.addToBackStack(null);

        transaction.commit();

    }
}
