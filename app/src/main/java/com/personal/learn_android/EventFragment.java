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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.personal.learn_android.model.Event;
import com.personal.learn_android.adapter.EventAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import io.realm.Realm;


public class EventFragment extends Fragment {

    @BindView(R.id.event_view)
    ListView listView;
    EventAdapter eventAdapter;

    private static final String ARG_ARRAY = "fragment_array_list_view";

    private Unbinder unbinder;

    private List<Event> eventList;
    Realm realm;

    public EventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        unbinder = ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        this.initial();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void initial() {
        eventList =  realm.where(Event.class).findAll();
        if (!eventList.isEmpty()) {
            eventAdapter = new EventAdapter(getActivity(), eventList);
            listView.setAdapter(eventAdapter);
        }
    }

    @OnItemClick(R.id.event_view)
    void onItemClicked(int position) {
        Event event = eventAdapter.getItem(position);
        if (event != null) {
            String message = event.getEventName();
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.putExtra(EventActivity.EXTRA_MESSAGE, message);
            getActivity().setResult(getActivity().RESULT_OK, intent);
            getActivity().finish();
        }
    }
}
