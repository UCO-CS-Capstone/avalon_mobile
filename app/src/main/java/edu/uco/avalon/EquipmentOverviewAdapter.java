package edu.uco.avalon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Michael Keller on 3/27/18.
 * Edited by Callie Driver on 4/3/18.
 *
 * To allow for a custom list item this adapter will create a view for every list item.
 */

public class EquipmentOverviewAdapter extends ArrayAdapter<Equipment> {
    // View lookup cache

    private int milestoneID, projectID;
    private ArrayList<Equipment> tempEquipment;
    private String tempStartDate, tempEndDate;
    private boolean temp = false;

    private static class ViewHolder {
        TextView tvEquipmentName;
        TextView tvEstEndDate;
        TextView tvCurrentCost;
    }

    public EquipmentOverviewAdapter(ArrayList<Equipment> data, Context context, int milestoneID, int projectID) {
        super(context, R.layout.equipment_overview_list_item_view, data);
        this.milestoneID = milestoneID;
        this.projectID = projectID;
    }

    public EquipmentOverviewAdapter(ArrayList<Equipment> data, Context context, int milestoneID, int projectID, boolean temp) {
        super(context, R.layout.equipment_overview_list_item_view, data);
        this.milestoneID = milestoneID;
        this.projectID = projectID;
        this.temp = temp;
    }

    public void setStrings(String start, String end){
        tempStartDate = start;
        tempEndDate = end;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.equipment_overview_list_item_view, parent, false);
            viewHolder.tvEquipmentName = convertView.findViewById(R.id.textEquipmentName);
            viewHolder.tvEstEndDate = convertView.findViewById(R.id.textEstEndDate);
            viewHolder.tvCurrentCost = convertView.findViewById(R.id.textCurrentCost);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the data item for this position
        final int pos = position;
        Equipment equipment = getItem(pos);

        viewHolder.tvEquipmentName.setText(equipment.getName());
        viewHolder.tvCurrentCost.setText(String.valueOf(equipment.getDaileyCost()));

        //Go through the list of projects' milestones' equipment list.
        boolean matchFound = false;
        boolean added = false;
        boolean didSomething = false;
        boolean overlappingDate = false;

        ArrayList<String> milestoneStartDateList = new ArrayList<>();
        ArrayList<String> milestoneEndDateList = new ArrayList<>();


        int count = equipment.getActiveCount();
        if(count > 0){
            if(temp == false){
                milestoneStartDateList.add(Project.projectList.get(projectID).milestones.get(milestoneID).getStartDate());
                milestoneEndDateList.add(Project.projectList.get(projectID).milestones.get(milestoneID).getEstEndDate());

                Outer1: for (Project p : Project.projectList) {

                    for (Milestone m : p.milestones) {

                        added = false;
                        if(p.milestones.size()-1 >= milestoneID) {
                            if (!p.milestones.get(milestoneID).equals(m)) { //the milestone from including itself
                                for (Equipment e : m.milestoneEquipmentList) {

                                    if (equipment.equals(e)) {

                                        if (added == false) {
                                            milestoneStartDateList.add(m.getStartDate());
                                            milestoneEndDateList.add(m.getEstEndDate());

                                            didSomething = true;

                                            added = true;
                                        }
                                        count--;
                                        matchFound = true;


                                        if (count <= 0) {
                                            break Outer1;
                                        }


                                    }
                                }
                            }
                        }

                    }
                }
            }
            else {
                milestoneStartDateList.add(tempStartDate);
                milestoneEndDateList.add(tempEndDate);

                Outer2: for (Project p : Project.projectList) {

                    for (Milestone m : p.milestones) {

                        added = false;

                            for (Equipment e : m.milestoneEquipmentList) {

                                if (equipment.equals(e)) {

                                    if (added == false) {
                                        milestoneStartDateList.add(m.getStartDate());
                                        milestoneEndDateList.add(m.getEstEndDate());

                                        didSomething = true;
                                        added = true;
                                    }
                                    count--;
                                    matchFound = true;


                                    if (count <= 0) {
                                        break Outer2;
                                    }

                                }
                            }


                    }
                }

            }

            if(didSomething == true) {
                overlappingDate = overlappingDates(milestoneStartDateList, milestoneEndDateList);
                milestoneStartDateList.clear();
                milestoneEndDateList.clear();
            }

        }

        if(overlappingDate == true){
            convertView.setBackgroundColor(Color.RED);
        }
        else if(matchFound == true){
            convertView.setBackgroundColor(Color.YELLOW);
        }
        else{
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        // Return the completed view to render on screen
        return convertView;
    }

    private boolean overlappingDates(ArrayList<String> start, ArrayList<String> end){
        try {

            //Check date stuff first
            Date start_date1, end_date1, start_date2, end_date2;
            if (!start.get(0).equals("")) {
                start_date1 = new SimpleDateFormat("MM/dd/yyyy").parse(start.get(0));
            } else {
                start_date1 = new Date();
            }

            if (!end.get(0).equals("")) {
                end_date1 = new SimpleDateFormat("MM/dd/yyyy").parse(end.get(0));
            } else {
                end_date1 = new Date();
            }

            for (int i = 1; i < start.size(); i++) {
                    if (!start.get(i).equals("")) {
                        start_date2 = new SimpleDateFormat("MM/dd/yyyy").parse(start.get(i));
                    } else {
                        start_date2 = new Date();
                    }

                    if (!end.get(i).equals("")) {
                        end_date2 = new SimpleDateFormat("MM/dd/yyyy").parse(end.get(i));
                    } else {
                        end_date2 = new Date();
                    }

                    if (!start.get(0).equals("") && !end.get(0).equals("") && !start.get(i).equals("") && !end.get(i).equals("")){

                        if (start_date1.before(end_date2) && end_date1.after(start_date2)) {

                            return true;
                        }

                    }
            }

        } catch(Exception e){
            Log.e("EquipOverviewAdapter", e.toString());
        }

        return false;
    }

}
