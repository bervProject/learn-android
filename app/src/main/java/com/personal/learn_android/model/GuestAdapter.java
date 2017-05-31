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

package com.personal.learn_android.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.personal.learn_android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuestAdapter extends BaseAdapter {
    private Context context;
    private List<Guest> guests;

    public GuestAdapter(Context context, List<Guest> guests) {
        this.context = context;
        this.guests = guests;
    }

    @Override
    public int getCount() {
        return guests.size();
    }

    @Override
    public Guest getItem(int position) {
        return guests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, final ViewGroup parent) {
        // Get the data item for this position
        Guest guest = getItem(position);
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_guest, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        // Populate the data into the template view using the data object
        holder.image.setImageResource(guest.getImage());
        holder.guestName.setText(guest.getName());

        // Return the completed view to render on screen
        return view;
    }

    class ViewHolder {
        @BindView(R.id.image_guest)
        ImageView image;
        @BindView(R.id.name_guest)
        TextView guestName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
