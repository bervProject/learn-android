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
package com.personal.learn_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.personal.learn_android.R
import com.personal.learn_android.model.Guest

class GuestAdapter(private val guests: List<Guest>) : BaseAdapter() {
    override fun getCount(): Int {
        return guests.size
    }

    override fun getItem(position: Int): Guest {
        return guests[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null) {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_guest, parent, false)
            // Get the data item for this position
            val guest = getItem(position)
            // Populate the data into the template view using the data object
            val image_guest = view.findViewById<ImageView>(R.id.image_guest)
            val name_guest = view.findViewById<TextView>(R.id.name_guest)
            image_guest.setImageResource(guest.image)
            name_guest.text = guest.name
            return view
        }
        return convertView
    }

}