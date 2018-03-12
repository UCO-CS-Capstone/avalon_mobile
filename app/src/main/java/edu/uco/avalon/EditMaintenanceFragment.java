package edu.uco.avalon;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cdcal on 2/7/2018.
 */

    public class EditMaintenanceFragment extends MaintenanceFragment {




    public EditMaintenanceFragment() {


    }

    @Override
    protected void addMaintenance() {




        Equipment.equipmentList.get(Maintenance.selected).maintenanceList.remove(Maintenance.id);
        String d = "";
        String comment = "";
        d += date.getText().toString();
        d += " " + time.getText().toString();
        comment += detail.getText().toString();

        String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(Calendar.getInstance().getTime());

        myDate inputDate = new myDate(d);
        myDate currentDate = new myDate(timeStamp);

        if (inputDate.compareto(currentDate) < 0)
            Toast.makeText(getContext(), "Date and time has to be in the future.", Toast.LENGTH_LONG).show();
        else {

            Equipment.equipmentList.get(Maintenance.selected).AddMaintenance(new Maintenance(d, comment));


            getFragmentManager().popBackStack();

        }
    }

    @Override
    protected  void deleteData(){

        Equipment.equipmentList.get(Maintenance.selected).maintenanceList.remove(Maintenance.id);
        getFragmentManager().popBackStack();

    }

}




