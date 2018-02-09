package edu.uco.avalon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michael Kelelr on 2/5/18.
 * To allow for a custom list item this adapter will create a view for every list item.
 */

public class ProjectOverviewAdapter extends ArrayAdapter<Project> {
    private ArrayList<Project> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView textProjectName;
        TextView textStartDate;
        TextView textEstEndDate;
        TextView textStatus;
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

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.project_overview_list_item_view, parent, false);
            viewHolder.textProjectName = (TextView) convertView.findViewById(R.id.textProjectName);
            viewHolder.textStartDate = (TextView) convertView.findViewById(R.id.textStartDate);
            viewHolder.textEstEndDate = (TextView) convertView.findViewById(R.id.textEndDate);
            viewHolder.textStatus = (TextView) convertView.findViewById(R.id.textStatus);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textProjectName.setText(project.getName());
        viewHolder.textStartDate.setText(project.getStartDate());
        viewHolder.textEstEndDate.setText(project.getEstEndDate());
        viewHolder.textStatus.setText(project.getStatus());

        // Return the completed view to render on screen
        return convertView;
    }
}
