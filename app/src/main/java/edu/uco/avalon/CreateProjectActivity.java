package edu.uco.avalon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.uco.avalon.Widgets.CurrencyEditText;

public class CreateProjectActivity extends AppCompatActivity{

    CurrencyEditText estCostEdit, currentCostEdit;
    EditText nameEdit;
    EditText startDateEdit, estEndDateEdit, actualEndDateEdit;
    Button saveButton;

    String name;
    String startDate, estEndDate, actualEndDate;
    double estCost, currentCost;
    String estCostString, currentCostString;

    DatePickerFragment pickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        pickerFragment = new DatePickerFragment();

        //Start of Project Name EditText ***********************************************************
        nameEdit = findViewById(R.id.projectNameEdit);
        //End of Project Name EditText *************************************************************

        //Start of StartDate, estEndDate, and currentEndDate EditTexts *****************************
        startDateEdit = findViewById(R.id.projectStartDateEdit);
        startDateEdit.setKeyListener(null);
        startDateEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    pickerFragment.setEditText(startDateEdit);
                    pickerFragment.show(getFragmentManager(), "datepicker");
                }
            }
        });
        startDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerFragment.setEditText(startDateEdit);
                pickerFragment.show(getFragmentManager(), "datepicker");
            }
        });

        estEndDateEdit = findViewById(R.id.projectEstEndDateEdit);
        estEndDateEdit.setKeyListener(null);
        estEndDateEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    pickerFragment.setEditText(estEndDateEdit);
                    pickerFragment.show(getFragmentManager(), "datepicker");
                }
            }
        });
        estEndDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerFragment.setEditText(estEndDateEdit);
                pickerFragment.show(getFragmentManager(), "datepicker");
            }
        });

        actualEndDateEdit = findViewById(R.id.projectActualEndDateEdit);
        actualEndDateEdit.setKeyListener(null);
        actualEndDateEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    pickerFragment.setEditText(actualEndDateEdit);
                    pickerFragment.show(getFragmentManager(), "datepicker");
                }
            }
        });
        actualEndDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerFragment.setEditText(actualEndDateEdit);
                pickerFragment.show(getFragmentManager(), "datepicker");
            }
        });
        //End of StartDate, estEndDate, and currentEndDate EditTexts *******************************

        //Start of Price EditTexts *****************************************************************
        estCostEdit = findViewById(R.id.projectEstCostEdit);
        currentCostEdit = findViewById(R.id.projectCurrentCostEdit);
        //End of Price EditTexts *******************************************************************

        //Start of Save Button *********************************************************************
        saveButton = findViewById(R.id.saveProjectButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEdit.getText().toString().trim();
                if(!name.equals("")) {
                    startDate = startDateEdit.getText().toString().trim();
                    estEndDate = estEndDateEdit.getText().toString().trim();
                    actualEndDate = actualEndDateEdit.getText().toString().trim();
                    fixMoneyString(); //fix possible errors with the money strings

                    Project newProject = new Project(name, startDate, estEndDate, actualEndDate, estCost, currentCost);
                    newProject.setEstCostString(estCostString);
                    newProject.setCurrentCostString((currentCostString));

                    Project.projectList.add(newProject);

                    //if saving was successful
                    Toast.makeText(CreateProjectActivity.this, "Saved Successfully.", Toast.LENGTH_SHORT).show();
                    finish();

                    //if saving was unsuccessful, don't go back to previous screen.
                    //Toast.makeText(getContext(), "Saved Successfully.", Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(CreateProjectActivity.this, "Please enter a name for the Project.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //End of Save Button ***********************************************************************
    }

    private void fixMoneyString(){
        String tempMoney = estCostEdit.getText().toString();
        if(tempMoney.equals("")){
            estCost = 0.00;
            estCostString = "$ 0.00";
        }
        else {
            //if the first character is a ".", concat a 0 onto the front of the dollar
            if (tempMoney.charAt(0) == '.') {
                tempMoney = "0" + tempMoney;
            }
            //if the string has a ".", make sure there is a 0 at the end if there is only 1 number after the "."
            if (tempMoney.contains(".") && tempMoney.indexOf(".") + 3 != tempMoney.length()) {
                tempMoney += "0";
            }
            estCostString = "$ " + tempMoney;
            //if there are commas in the string, take them out so it can be converted to a double.
            if(tempMoney.contains(",")){
                tempMoney = tempMoney.replaceAll(",", "");
            }
            estCost = Double.parseDouble((tempMoney));
        }

        tempMoney = currentCostEdit.getText().toString();
        if(tempMoney.equals("")){
            currentCost = 0.00;
            currentCostString = "$ 0.00";
        }
        else {
            //if the first character is a ".", concat a 0 onto the front of the dollar
            if (tempMoney.charAt(0) == '.') {
                tempMoney = "0" + tempMoney;
            }
            //if the string has a ".", make sure there is a 0 at the end if there is only 1 number after the "."
            if (tempMoney.contains(".") && tempMoney.indexOf(".") + 3 != tempMoney.length()) {
                tempMoney += "0";
            }
            currentCostString = "$ " + tempMoney;
            //if there are commas in the string, take them out so it can be converted to a double.
            if(tempMoney.contains(",")){
                tempMoney = tempMoney.replaceAll(",", "");
            }
            currentCost = Double.parseDouble((tempMoney));
        }
    }
}
