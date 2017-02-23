package com.personal.learn_android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;


public class EventActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.personal.learn_android.EVENT_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // Construct the data source
        ArrayList<EventModel> arrayOfUsers = new ArrayList<EventModel>();
        // Create the adapter to convert the array to views
        final EventAdapter adapter = new EventAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.event_view);
        EventModel event1 = new EventModel("Makan-Makan","12 Agustus 2017",R.drawable.events);
        EventModel event2 = new EventModel("Liburan","19 Agustus 2017",R.drawable.sample_0);
        EventModel event3 = new EventModel("Kasih Makan","13 Agustus 2017",R.drawable.sample_2);
        EventModel event4 = new EventModel("Jalan Jalan","17 Agustus 2017",R.drawable.sample_3);
        adapter.add(event1);
        adapter.add(event2);
        adapter.add(event3);
        adapter.add(event4);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String message= adapter.getItem(position).getEventName();
                Intent intent = new Intent(EventActivity.this,HomeActivity.class);
                intent.putExtra(EXTRA_MESSAGE,message);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        String message = null;
        Intent intent = new Intent(EventActivity.this,HomeActivity.class);
        intent.putExtra(EXTRA_MESSAGE,message);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
