package edu.uco.avalon;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Created by cdcal on 2/7/2018.
 */

public class MaintenanceFragment extends Fragment {
    Button save;
    EditText date;
    EditText time;
    EditText detail;

    public MaintenanceFragment(){


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maintenance, container, false);

        date = (EditText) view.findViewById(R.id.DateMaintenance);
        time = (EditText) view.findViewById(R.id.TimeMaintenance);
        detail = (EditText) view.findViewById(R.id.maintenanceDetail);


        save = (Button) view.findViewById(R.id.saveMaintenanceButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addMaintenance();

            }
        });
        return view;

    }
    private void addMaintenance(){
        String d = "";
        String comment="";
        d += date.getText().toString();
        d += " "+time.getText().toString();
        comment += detail.getText().toString();

        String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(Calendar.getInstance().getTime());

        myDate inputDate = new myDate(d);
        myDate currentDate = new myDate(timeStamp);

        if(inputDate.compareto(currentDate) <0) Toast.makeText(getContext(), "Date and time has to be in the future.", Toast.LENGTH_LONG).show();
        else {

            Equipment.equipmentList.get(Maintenance.selected).AddMaintenance(new Maintenance(d, comment));


            getFragmentManager().popBackStack();
        }


    }
    class myDate{
        public int day;
        public int month;
        public int year;
        public int hour;
        public int min;

        public myDate(String d){
            month = Integer.parseInt(d.substring(0, 2));
            day = Integer.parseInt(d.substring(3, 5));
            year = Integer.parseInt(d.substring(6, 10));
            hour = Integer.parseInt(d.substring(11, 13));
            min = Integer.parseInt(d.substring(14));


        }
        public String timestamp(){
            return "" + day + "/" + month + "/" + year + " " + hour + ":" + min;
        }
        public int compareto(myDate d){
            int r=1;
            if(year < d.year) r= -1;
            else if(year == d.year && month < d.month) r= -1;
            else if(year == d.year && month == d.month && day < d.day) r= -1;
            else if(year == d.year && month == d.month && day == d.day && hour < d.hour) r= -1;
            else if(year == d.year && month == d.month && day == d.day && hour == d.hour && min <= d.min) r= -1;
            else;
            return r;

        }
    }
}
