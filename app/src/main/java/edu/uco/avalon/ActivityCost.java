package edu.uco.avalon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import edu.uco.avalon.ProjectAnalysis.Equipments;
import edu.uco.avalon.ProjectAnalysis.Projects;

public class ActivityCost extends AppCompatActivity {

    Spinner projectSpinner;
    Projects project1;
    Projects project2;
    Projects project3;
    Equipments equip1;
    Equipments equip2;
    Equipments equip3;
    Equipments equip4;
    Equipments equip5;
    SimpleDateFormat format;
    Button analysis;
    CheckBox chk1;
    CheckBox chk2;
    CheckBox chk3;
    CheckBox chk4;
    CheckBox chk5;
    TextView totalCost;
    TextView equipCost;
    TextView maintCost;
    EditText mainCostEnter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);
        format = new SimpleDateFormat("MM/dd/yyyy");
        analysis = (Button)findViewById(R.id.btn_analyze);
        projectSpinner = (Spinner)findViewById(R.id.project_spinner);
        String[] s = {"Project 1", "Project 2", "Project 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, s);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        projectSpinner.setAdapter(adapter);


        chk1 = (CheckBox)findViewById(R.id.checkBox1);
        chk2 = (CheckBox)findViewById(R.id.checkBox2);
        chk3 = (CheckBox)findViewById(R.id.checkBox3);
        chk4 = (CheckBox)findViewById(R.id.checkBox4);
        chk5 = (CheckBox)findViewById(R.id.checkBox5);

        totalCost = (TextView)findViewById(R.id.txt_total_cost);
        equipCost = (TextView)findViewById(R.id.txt_equip_cost);
        maintCost = (TextView)findViewById(R.id.txt_maint_cost);
        mainCostEnter = (EditText)findViewById(R.id.edtxt_maint);

        project1 = new Projects();
        project1.setProjectDateBegin("03/19/2018");
        project1.setProjectDateEnd("04/13/2018");


        project2 = new Projects();
        project2.setProjectDateBegin("01/11/2018");
        project2.setProjectDateEnd("02/28/2018");

        project3 = new Projects();
        project3.setProjectDateBegin("05/04/2018");
        project3.setProjectDateEnd("06/10/2018");

        equip1 = new Equipments(1, 1000);
        equip2 = new Equipments(2, 1045);
        equip3 = new Equipments(3, 934);
        equip4 = new Equipments(4,1200);
        equip5 = new Equipments(5, 550);

    }

    public void onAnalysisClick(View view) {
        int costAnalysisT = 0, costAnalysisE = 0, costAnalysisM = 0;
        if(mainCostEnter.getText().toString().equals("")) {
            Toast.makeText(ActivityCost.this, "Please do not leave maintenance cost field blank.", Toast.LENGTH_LONG).show();
        } else {
            int costOfMaint = Integer.parseInt(mainCostEnter.getText().toString());
            int equipmentTally = 0;
            if(chk1.isChecked()) {
                equipmentTally++;
                costAnalysisE += equip1.getCost();
            }
            if(chk2.isChecked()) {
                equipmentTally++;
                costAnalysisE += equip2.getCost();
            }
            if(chk3.isChecked()) {
                equipmentTally++;
                costAnalysisE += equip3.getCost();
            }
            if(chk4.isChecked()) {
                equipmentTally++;
                costAnalysisE += equip4.getCost();
            }
            if(chk5.isChecked()) {
                equipmentTally++;
                costAnalysisE += equip5.getCost();
            }
            if(projectSpinner.getSelectedItem().toString().equals("Project 1")) {
                costAnalysisM = (TotalDaySpread(project1) + equipmentTally) * costOfMaint;
                maintCost.setText(Integer.toString(costAnalysisM));
                costAnalysisE = TotalDaySpread(project1) * costAnalysisE;
                equipCost.setText(Integer.toString(costAnalysisE));
                costAnalysisT = costAnalysisM + costAnalysisE;
                totalCost.setText(Integer.toString(costAnalysisT));
            }
            if(projectSpinner.getSelectedItem().toString().equals("Project 2")) {
                costAnalysisM = (TotalDaySpread(project2) + equipmentTally) * costOfMaint;
                maintCost.setText(Integer.toString(costAnalysisM));
                costAnalysisE = TotalDaySpread(project2) * costAnalysisE;
                equipCost.setText(Integer.toString(costAnalysisE));
                costAnalysisT = costAnalysisM + costAnalysisE;
                totalCost.setText(Integer.toString(costAnalysisT));
            }
            if(projectSpinner.getSelectedItem().toString().equals("Project 3")) {
                costAnalysisM = (TotalDaySpread(project3) + equipmentTally) * costOfMaint;
                maintCost.setText(Integer.toString(costAnalysisM));
                costAnalysisE = TotalDaySpread(project3) * costAnalysisE;
                equipCost.setText(Integer.toString(costAnalysisE));
                costAnalysisT = costAnalysisM + costAnalysisE;
                totalCost.setText(Integer.toString(costAnalysisT));
            }
        }
    }

    public int TotalDaySpread(Projects project) {
            int totalTime = findBeginTime(project) + findEndTime(project);
        return totalTime;
    }

    public int findBeginTime(Projects project) {
        String s = this.format.format(project.getProjectDateBegin()).toString();
        String[] tokens = s.split("/");
        int i = Integer.parseInt(tokens[0]);
        System.out.println("This is the value of token[1]" + tokens[1]);
        int daysOfMonth = 0;
        switch(i) {
            case 1:
                daysOfMonth = 31;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 2:
                daysOfMonth = 28;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 3:
                daysOfMonth = 31;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 4:
                daysOfMonth = 30;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 5:
                daysOfMonth = 31;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 6:
                daysOfMonth = 30;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 7:
                daysOfMonth = 31;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 8:
                daysOfMonth = 31;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 9:
                daysOfMonth = 30;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 10:
                daysOfMonth = 31;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 11:
                daysOfMonth = 30;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
            case 12:
                daysOfMonth = 31;
                i = daysOfMonth - Integer.parseInt(tokens[1]);
                break;
        }
        return i;
    }

    public int findEndTime(Projects project) {
        String s = this.format.format(project.getProjectDateEnd()).toString();
        String[] tokens = s.split("/");
        int i = Integer.parseInt(tokens[1]);
        return i;
    }
}
