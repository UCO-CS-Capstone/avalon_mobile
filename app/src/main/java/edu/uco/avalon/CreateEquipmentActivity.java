package edu.uco.avalon;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class CreateEquipmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_equipment);


        Fragment addedFragment = new CreateEquipmentFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, addedFragment);
        transaction.addToBackStack(null);

        transaction.commit();

    }
}
