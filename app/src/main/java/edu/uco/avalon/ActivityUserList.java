package edu.uco.avalon;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ActivityUserList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        Fragment addedFragment = new UserListFragment();
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
//        transaction.replace(R.tempID.fragment_container, addedFragment);
//                transaction.addToBackStack(null);
//
//                transaction.commit();