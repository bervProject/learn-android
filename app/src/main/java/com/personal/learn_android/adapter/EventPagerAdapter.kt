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

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.personal.learn_android.EventPagerFragment
import com.personal.learn_android.model.Event

/**
 * Created by Bervianto Leo P on 03/06/2017.
 */
class EventPagerAdapter(fm: FragmentManager?, private val eventList: List<Event>) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return EventPagerFragment.Companion.newInstance(eventList[position].image, eventList[position].eventName)
    }

    override fun getCount(): Int {
        return eventList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return eventList[position].eventName
    }

}