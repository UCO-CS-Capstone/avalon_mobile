package edu.uco.avalon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class ProjectReportAdapter extends ArrayAdapter<Project> {
    private ArrayList<Project> dataSet;
    Context mContext;
    private Project project;

    // View lookup cache
    private static class ViewHolder {
        TextView textProjectName;
        TextView textEstEndDate;
        TextView textCurrentCost;
        TextView tvCurrentMilestone;

        Button buttonStatus;
    }

    public ProjectReportAdapter(ArrayList<Project> data, Context context) {
        super(context, R.layout.project_report_list_item_view, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        project = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        final int pos = position;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.project_report_list_item_view, parent, false);
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
                Intent intent = new Intent(mContext, ProjectReport.class);
                intent.putExtra("Position", pos);
                intent.putExtra("NAME", viewHolder.textProjectName.getText().toString());
                intent.putExtra("END",viewHolder.textEstEndDate.getText().toString());
                intent.putExtra("COST",viewHolder.textCurrentCost.getText().toString());
                intent.putExtra("MILESTONE",viewHolder.tvCurrentMilestone.getText().toString());

                mContext.startActivity(intent);

            }
        });

        viewHolder.textProjectName.setText(project.getName());
        viewHolder.textEstEndDate.setText(project.getEstEndDate());
        viewHolder.textCurrentCost.setText(String.valueOf(project.getCurrentCost()));
        viewHolder.tvCurrentMilestone.setText(project.getCurrentMilestone());

        return convertView;
    }

}
