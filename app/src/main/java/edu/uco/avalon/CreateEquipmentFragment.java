package edu.uco.avalon;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CreateEquipmentFragment extends Fragment implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    EditText nameEdit;
    Spinner typeSpinner;
    EditText maintDateEdit, maintTimeEdit, maintTimerEdit, tempTimeEdit;
    EditText priceDollarEdit, priceCentsEdit;
    Button saveButton;
    TextView dollarText;

    String name;
    String type;
    String maintDate, maintTime, maintTimer;
    String price, priceDollars, priceCents;

    int day, month, year, hour, minute;
    //Dummy values to be replaced by accessing the Database
    String typeList[] = {"Type 1", "Type 2", "Type 3", "Type 4"}; //should be accessed from db


    public CreateEquipmentFragment() {
        // Required empty public constructor

        //if creating new equipment, just get the types and nothing else.

        //if editing equipment, get that data and store into all the edit texts and such
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_equipment, container, false);

        //Start of Project Name EditText ***********************************************************
        nameEdit = view.findViewById(R.id.equipmentNameEdit);
        //End of Project Name EditText *************************************************************

        //Start of Type Spinner ********************************************************************
        typeSpinner =  view.findViewById(R.id.typeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),
                R.layout.spinner_equipment_type, typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                type = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type = typeList[0];
            }
        });
        //End of Type Spinner **********************************************************************

        //Start of Maintenance Date/Time/Timer EditTexts *******************************************
        maintDateEdit = view.findViewById(R.id.equipmentMaintDateEdit);
        maintDateEdit.setKeyListener(null);
        maintDateEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);

                    new DatePickerDialog(getContext(),CreateEquipmentFragment.this,
                            year, month, day).show();
                }
            }
        });
        maintDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);

                    new DatePickerDialog(getContext(),CreateEquipmentFragment.this,
                            year, month, day).show();
            }
        });

        maintTimeEdit = view.findViewById(R.id.equipmentMaintTimeEdit);
        maintTimeEdit.setKeyListener(null);
        maintTimeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Calendar c = Calendar.getInstance();
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);

                    tempTimeEdit = maintTimeEdit;

                    new TimePickerDialog(getContext(),CreateEquipmentFragment.this,
                            hour, minute, DateFormat.is24HourFormat(getContext())).show();
                }
            }
        });
        maintTimeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                tempTimeEdit = maintTimeEdit;

                new TimePickerDialog(getContext(),CreateEquipmentFragment.this,
                        hour, minute, DateFormat.is24HourFormat(getContext())).show();
            }
        });

        maintTimerEdit = view.findViewById(R.id.equipmentMaintTimerEdit);
        maintTimerEdit.setKeyListener(null);
        maintTimerEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Calendar c = Calendar.getInstance();
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);

                    tempTimeEdit = maintTimerEdit;

                    new TimePickerDialog(getContext(),CreateEquipmentFragment.this,
                            hour, minute, DateFormat.is24HourFormat(getContext())).show();
                }
            }
        });
        maintTimerEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                tempTimeEdit = maintTimerEdit;

                new TimePickerDialog(getContext(),CreateEquipmentFragment.this,
                        hour, minute, DateFormat.is24HourFormat(getContext())).show();
            }
        });
        //End of Maintenance Date/Time/Timer EditTexts *********************************************

        //Start of Price EditTexts *****************************************************************
        priceDollarEdit = view.findViewById(R.id.equipmentPriceDollarEdit);
        priceCentsEdit = view.findViewById(R.id.equipmentPriceCentsEdit);
        //End of Price EditTexts *******************************************************************

        //Start of Save Button *********************************************************************
        saveButton = view.findViewById(R.id.saveEquipmentButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEdit.getText().toString().trim();
                type = typeSpinner.getSelectedItem().toString().trim();
                maintDate = maintDateEdit.getText().toString().trim();
                maintTime = maintTimeEdit.getText().toString().trim();
                maintTimer = maintTimerEdit.getText().toString().trim();
                priceDollars = priceDollarEdit.getText().toString().trim();
                priceCents = priceCentsEdit.getText().toString().trim();

                //  if(price)


                //if saving was successful
                Toast.makeText(getContext(), "Saved Successfully.", Toast.LENGTH_SHORT);

                //if saving was unsuccessful, don't go back to previous screen.
                //Toast.makeText(getContext(), "Saved Successfully.", Toast.LENGTH_SHORT);
            }
        });
        //End of Save Button ***********************************************************************



        return view;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        year = i;
        month = i1;
        day = i2;

        String text = month + "\\" + day + "\\" + year;
        maintDateEdit.setText(text);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hour = i;
        minute = i1;
        String text = "";



        if(minute < 10){
            text = hour + ":0" + minute;
        }
        else {
            text = hour + ":" + minute;
        }

        tempTimeEdit.setText(text);
    }
}
