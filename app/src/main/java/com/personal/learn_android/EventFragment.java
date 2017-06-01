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
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.personal.learn_android.model.Event;
import com.personal.learn_android.model.EventAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;


public class EventFragment extends Fragment {

    @BindView(R.id.event_view)
    ListView listView;
    EventAdapter eventAdapter;

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.initial();
        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void initial() {
        List<Event> listOfEvent = new ArrayList<>();

        Event event1 = new Event("Event 1", "12 Agustus 2017", R.drawable.events, 10.2, 10.2);
        Event event2 = new Event("Event 2", "19 Agustus 2017", R.drawable.events, 20.3, 20.3);
        Event event3 = new Event("Event 3", "13 Agustus 2017", R.drawable.events, 20.3, 10.3);
        Event event4 = new Event("Event 4", "17 Agustus 2017", R.drawable.events, 10.3, 20.3);
        listOfEvent.add(event1);
        listOfEvent.add(event2);
        listOfEvent.add(event3);
        listOfEvent.add(event4);

        // Create the adapter to convert the array to views
        eventAdapter = new EventAdapter(getActivity(), listOfEvent);
        listView.setAdapter(eventAdapter);
    }

    @OnItemClick(R.id.event_view)
    void onItemClicked(int position) {
        Event event = eventAdapter.getItem(position);
        if (event != null) {
            // String message = event.getEventName();
            // Intent intent = new Intent(EventActivity.this, HomeActivity.class);
            // intent.putExtra(EXTRA_MESSAGE, message);
            // setResult(Activity.RESULT_OK, intent);
            // finish();
        }
    }


}
