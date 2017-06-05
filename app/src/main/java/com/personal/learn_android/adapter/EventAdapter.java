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

package com.personal.learn_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.personal.learn_android.R;
import com.personal.learn_android.model.Event;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventAdapter extends BaseAdapter {

    private Context context;
    private List<Event> events;


    public EventAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Event getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, final ViewGroup parent) {
        // Get the data item for this position
        Event event = getItem(position);
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        // Populate the data into the template view using the data object
        holder.image.setImageResource(event.getImage());
        holder.eventName.setText(event.getEventName());
        holder.eventDate.setText(event.getTanggalEvent());
        // Return the completed view to render on screen
        return view;
    }

    class ViewHolder {
        @BindView(R.id.imageEvent)
        ImageView image;
        @BindView(R.id.eventName)
        TextView eventName;
        @BindView(R.id.eventDate)
        TextView eventDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
