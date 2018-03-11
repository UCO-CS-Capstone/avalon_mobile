package edu.uco.avalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
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
    Button visualize;
    Switch swtch1;
    Switch swtch2;
    Switch swtch3;
    Switch swtch4;
    Switch swtch5;
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
        visualize = (Button)findViewById(R.id.btn_vis);
        projectSpinner = (Spinner)findViewById(R.id.project_spinner);
        String[] s = {"Project 1", "Project 2", "Project 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, s);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        projectSpinner.setAdapter(adapter);


        swtch1 = (Switch) findViewById(R.id.switch1);
        swtch2 = (Switch) findViewById(R.id.switch2);
        swtch3 = (Switch) findViewById(R.id.switch3);
        swtch4 = (Switch) findViewById(R.id.switch4);
        swtch5 = (Switch) findViewById(R.id.switch5);

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
            if(swtch1.isChecked()) {
                equipmentTally++;
                costAnalysisE += equip1.getCost();
            }
            if(swtch2.isChecked()) {
                equipmentTally++;
                costAnalysisE += equip2.getCost();
            }
            if(swtch3.isChecked()) {
                equipmentTally++;
                costAnalysisE += equip3.getCost();
            }
            if(swtch4.isChecked()) {
                equipmentTally++;
                costAnalysisE += equip4.getCost();
            }
            if(swtch5.isChecked()) {
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

    public void startGraphView(View view) {
        if(!this.totalCost.getText().toString().equals("")) {
            Intent intent = new Intent(this, GraphViewActivity.class);
            intent.putExtra("EQUIP", equipCost.getText().toString());
            intent.putExtra("MAINT", maintCost.getText().toString());
            intent.putExtra("TOTAL", totalCost.getText().toString());
            startActivity(intent);
        }
    }
}
