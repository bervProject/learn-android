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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.personal.learn_android.EventPagerFragment;

import java.util.List;

/**
 * Created by Bervianto Leo P on 03/06/2017.
 */

public class EventPagerAdapter extends FragmentPagerAdapter {
    private List<Event> eventList;

    public EventPagerAdapter(FragmentManager fm, List<Event> listEvent) {
        super(fm);
        eventList = listEvent;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return EventPagerFragment.newInstance(eventList.get(position).getImage(), eventList.get(position).getEventName());
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return eventList.get(position).getEventName();
    }
}
