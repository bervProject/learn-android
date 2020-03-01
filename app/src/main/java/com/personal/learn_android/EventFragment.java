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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.personal.learn_android.adapter.EventAdapter;
import com.personal.learn_android.model.Event;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import io.realm.Realm;


public class EventFragment extends Fragment {

    @BindView(R.id.event_view)
    protected ListView listView;
    private EventAdapter eventAdapter;
    private Unbinder unbinder;
    private Realm realm;

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

    private void initial() {
        List<Event> eventList = realm.where(Event.class).findAll();
        if (!eventList.isEmpty()) {
            eventAdapter = new EventAdapter(getActivity(), eventList);
            listView.setAdapter(eventAdapter);
        }
    }

    @OnItemClick(R.id.event_view)
    void onItemClicked(int position) {
        Event event = eventAdapter.getItem(position);
        if (event != null) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                String message = event.getEventName();
                Intent intent = new Intent(activity, HomeActivity.class);
                intent.putExtra(EventActivity.EXTRA_MESSAGE, message);
                activity.setResult(FragmentActivity.RESULT_OK, intent);
                activity.finish();
            }
        }
    }
}
