/*
 * Copyright (c) 2017 Bervianto Leo Pratama
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.personal.learn_android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.personal.learn_android.model.Event;
import com.personal.learn_android.model.EventAdapter;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.personal.learn_android.EVENT_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // Construct the data source
        ArrayList<Event> arrayOfUsers = new ArrayList<Event>();
        // Create the adapter to convert the array to views
        final EventAdapter adapter = new EventAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.event_view);
        Event event1 = new Event("Makan-Makan", "12 Agustus 2017", R.drawable.events);
        Event event2 = new Event("Liburan", "19 Agustus 2017", R.drawable.sample_0);
        Event event3 = new Event("Kasih Makan", "13 Agustus 2017", R.drawable.sample_2);
        Event event4 = new Event("Jalan Jalan", "17 Agustus 2017", R.drawable.sample_3);
        adapter.add(event1);
        adapter.add(event2);
        adapter.add(event3);
        adapter.add(event4);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Event event = adapter.getItem(position);
                if (event != null) {
                    String message = event.getEventName();
                    Intent intent = new Intent(EventActivity.this, HomeActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, message);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        String message = null;
        Intent intent = new Intent(EventActivity.this, HomeActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
