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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.personal.learn_android.R;
import com.personal.learn_android.model.Guest;

import java.util.ArrayList;

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
