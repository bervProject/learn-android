package com.personal.learn_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bervianto Leo P on 22/02/2017.
 */

public class GuestAdapter extends ArrayAdapter<Guest> {

    public GuestAdapter(Context context, ArrayList<Guest> guests) {
        super(context, 0, guests);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        Guest guest = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_guest, parent, false);
        }

        // Lookup view for data population
        ImageView image = (ImageView) convertView.findViewById(R.id.image_guest);
        TextView guestName = (TextView) convertView.findViewById(R.id.name_guest);

        // Populate the data into the template view using the data object
        image.setImageResource(guest.getImage());
        guestName.setText(guest.getName());

        // Return the completed view to render on screen
        return convertView;

    }

}
