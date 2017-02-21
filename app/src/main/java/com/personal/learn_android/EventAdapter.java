package com.personal.learn_android;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bervianto Leo P on 21/02/2017.
 */

public class EventAdapter extends ArrayAdapter<EventModel> {

    public final static String EXTRA_MESSAGE = "com.personal.learn_android.EVENT_MESSAGE";

    public EventAdapter(Context context, ArrayList<EventModel> events) {
        super(context, 0, events);
    }

    @Override

    public View getView(int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        EventModel event = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
        }

        // Lookup view for data population
        ImageView image = (ImageView) convertView.findViewById(R.id.imageEvent);
        TextView eventName = (TextView) convertView.findViewById(R.id.eventName);
        TextView eventDate = (TextView) convertView.findViewById(R.id.eventDate);

        // Populate the data into the template view using the data object
        image.setImageResource(event.getImage());
        eventName.setText(event.getEventName());
        eventDate.setText(event.getTanggalEvent());

        // Return the completed view to render on screen
        return convertView;

    }

    public EventModel getItem(int position) {
        return getItem(position);
    }

}
