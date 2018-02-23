package edu.uco.avalon;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class EquipmentOptionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        Equipment.typeList.add("Type 1");
        Equipment.typeList.add("Type 2");
        Equipment.typeList.add("Type 3");
        Equipment.typeList.add("Type 4");

        Fragment addedFragment = new EquipmentOptionsFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, addedFragment);
        //transaction.addToBackStack(null); //don't add first fragment to backstack, or you will get
                                            //a blank activity when backbutton is pressed.
        transaction.commit();

    }
}


//    Fragment addedFragment = new CreateEquipmentFragment();
//    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//        transaction.replace(R.id.fragment_container, addedFragment);
//                transaction.addToBackStack(null);
//
//                transaction.commit();