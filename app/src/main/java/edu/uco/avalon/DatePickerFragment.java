package edu.uco.avalon;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;


/**
 * Created by cdcal on 3/5/2018.
 */

public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    public String date = "";

    private EditText edit;

    public void setEditText(EditText e){
        edit = e;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

// Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        return dialog;

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        month++;
        date = month + "/" + day + "/" + year;

        if(edit != null) {
            edit.setText(date);
        }
    }

}
