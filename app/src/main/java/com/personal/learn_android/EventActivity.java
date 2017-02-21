package com.personal.learn_android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.personal.learn_android.EVENT_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // Construct the data source
        ArrayList<EventModel> arrayOfUsers = new ArrayList<EventModel>();
        // Create the adapter to convert the array to views
        EventAdapter adapter = new EventAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.event_view);
        EventModel event1 = new EventModel("Makan-makan","12-08-2017",R.drawable.events);
        EventModel event2 = new EventModel("Makan-makan","12-08-2017",R.drawable.events);
        EventModel event3 = new EventModel("Makan-makan","12-08-2017",R.drawable.events);
        EventModel event4 = new EventModel("Makan-makan","12-08-2017",R.drawable.events);
        adapter.add(event1);
        adapter.add(event2);
        adapter.add(event3);
        adapter.add(event4);
        listView.setAdapter(adapter);
    }



}
