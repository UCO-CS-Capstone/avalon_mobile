package edu.uco.avalon;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by cdcal on 2/7/2018.
 */

    public class EditUserFragment extends AddUserFragment {


    public EditUserFragment() {


    }

    @Override
    public void addUser() {



/*
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
*/
    }
}




