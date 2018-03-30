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
 * Created by Michael Keller on 3/27/18.
 *
 * To allow for a custom list item this adapter will create a view for every list item.
 */

public class EquipmentOverviewAdapter extends ArrayAdapter<Equipment> {
    // View lookup cache
    private static class ViewHolder {
        TextView tvEquipmentName;
        TextView tvEstEndDate;
        TextView tvCurrentCost;
    }

    public EquipmentOverviewAdapter(ArrayList<Equipment> data, Context context) {
        super(context, R.layout.equipment_overview_list_item_view, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Equipment equipment = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final int pos = position;

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

        viewHolder.tvEquipmentName.setText(equipment.getName());
        viewHolder.tvCurrentCost.setText(String.valueOf(equipment.getDaileyCost()));

        // Return the completed view to render on screen
        return convertView;
    }
}
