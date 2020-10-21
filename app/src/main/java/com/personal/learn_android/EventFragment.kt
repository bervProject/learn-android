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
package com.personal.learn_android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.personal.learn_android.adapter.EventAdapter
import com.personal.learn_android.model.Event
import io.realm.Realm

class EventFragment : Fragment() {

    private var eventAdapter: EventAdapter? = null
    private lateinit var realm: Realm

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_event, container, false)
        realm = Realm.getDefaultInstance()
        val eventView = view.findViewById<ListView>(R.id.event_view)
        eventView.setOnItemClickListener { _, _, position, _ ->
            val event = eventAdapter?.getItem(position)
            if (event != null) {
                val activity = activity
                if (activity != null) {
                    val message = event.eventName
                    val intent = Intent(activity, HomeActivity::class.java)
                    intent.putExtra(EventActivity.EXTRA_MESSAGE, message)
                    activity.setResult(FragmentActivity.RESULT_OK, intent)
                    activity.finish()
                }
            }
        }
        val eventList: List<Event> = realm.where(Event::class.java).findAll()
        if (eventList.isNotEmpty()) {
            eventAdapter = EventAdapter(eventList)
            eventView.adapter = eventAdapter
        }
        return view
    }
}