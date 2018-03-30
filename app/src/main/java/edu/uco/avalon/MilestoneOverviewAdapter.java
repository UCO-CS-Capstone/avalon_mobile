package edu.uco.avalon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michael Keller on 3/20/18.
 *
 * Show the important info about a milestone
 */

public class MilestoneOverviewAdapter extends ArrayAdapter<Milestone> {
    // View lookup cache
    private static class ViewHolder {
        TextView textMilestoneName;
        TextView textEstEndDate;
        TextView textCurrentCost;
    }

    MilestoneOverviewAdapter(ArrayList<Milestone> data, Context context) {
        super(context, R.layout.milestone_overview_list_item_view, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Milestone milestone = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        MilestoneOverviewAdapter.ViewHolder viewHolder; // view lookup cache stored in tag
        final int pos = position;
        if (convertView == null) {
            viewHolder = new MilestoneOverviewAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.milestone_overview_list_item_view, parent,
                    false);
            viewHolder.textMilestoneName = convertView.findViewById(R.id.textEquipmentName);
            viewHolder.textEstEndDate = convertView.findViewById(R.id.textEstEndDate);
            viewHolder.textCurrentCost = convertView.findViewById(R.id.textCurrentCost);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MilestoneOverviewAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.textMilestoneName.setText(milestone.getMilestoneName());
        viewHolder.textEstEndDate.setText(milestone.getEstEndDate());
        viewHolder.textCurrentCost.setText(String.valueOf(milestone.getCost()));

        return convertView;
    }
}