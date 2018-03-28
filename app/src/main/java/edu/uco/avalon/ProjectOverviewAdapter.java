package edu.uco.avalon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michael Keller on 2/5/18.
 * Edited by Callie Driver on 3/6/2018.
 * Edited by Michael Keller 0n 3/16/2018
 *        Cleaned up the code and added milestone to the projects
 *
 * To allow for a custom list item this adapter will create a view for every list item.
 */

public class ProjectOverviewAdapter extends ArrayAdapter<Project> {
    private ArrayList<Project> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView textProjectName;
        TextView textEstEndDate;
        TextView textCurrentCost;
        TextView tvCurrentMilestone;

        Button buttonStatus;
    }

    public ProjectOverviewAdapter(ArrayList<Project> data, Context context) {
        super(context, R.layout.project_overview_list_item_view, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Project project = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final int pos = position;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.project_overview_list_item_view, parent, false);
            viewHolder.textProjectName = convertView.findViewById(R.id.textEquipmentName);
            viewHolder.textEstEndDate = convertView.findViewById(R.id.textEstEndDate);
            viewHolder.textCurrentCost = convertView.findViewById(R.id.textCurrentCost);
            viewHolder.buttonStatus= convertView.findViewById(R.id.buttonStatus);
            viewHolder.tvCurrentMilestone = convertView.findViewById(R.id.tvCurrentMilestone);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.buttonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the status button is pressed, go to the next activity to show the issues. (the status string)
                Intent intent = new Intent(mContext, ProjectStatusActivity.class);
                intent.putExtra("Position", pos);

                mContext.startActivity(intent);

            }
        });

        viewHolder.textProjectName.setText(project.getName());
        viewHolder.textEstEndDate.setText(project.getEstEndDate());
        viewHolder.textCurrentCost.setText(project.getCurrentCostString());
        viewHolder.tvCurrentMilestone.setText(project.getCurrentMilestone());


        //if project.checkStatus() == true, change button color to yellow,
        // 0          = no errors                         = BLUE
        // 1          = warning                           = ORANGE
        // 2          = important warning                 = RED
        // 3          = finished project with errors      = YELLOW
        // 4          = finished project with no errors.  = GREEN
        int warningLevel = project.checkStatus();
        if(warningLevel == 0){
            viewHolder.buttonStatus.setBackgroundColor(Color.CYAN);
        }
        else if (warningLevel == 1){
            viewHolder.buttonStatus.setBackgroundColor(Color.parseColor("#FFA500"));
        }
        else if (warningLevel == 2){
            viewHolder.buttonStatus.setBackgroundColor(Color.RED);
        }
        else if (warningLevel == 3){
            viewHolder.buttonStatus.setBackgroundColor(Color.YELLOW);
        }
        else if (warningLevel == 4){
            viewHolder.buttonStatus.setBackgroundColor(Color.GREEN);
        }

        // Return the completed view to render on screen
        return convertView;
    }

}
