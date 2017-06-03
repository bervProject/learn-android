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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Bervianto Leo P on 03/06/2017.
 */

public class EventPagerFragment extends Fragment {
    private static final String ARG_SECTION_IMAGE = "section_image";
    private static final String ARG_SECTION_NAME = "section_name";

    @BindView(R.id.event_map_image)
    ImageView image;
    @BindView(R.id.event_map_text)
    TextView textView;

    private Unbinder unbinder;

    public EventPagerFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static EventPagerFragment newInstance(Integer image, String name) {
        EventPagerFragment fragment = new EventPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NAME, name);
        args.putInt(ARG_SECTION_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_event_map, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        textView.setText(getArguments().getString(ARG_SECTION_NAME));
        image.setImageResource(getArguments().getInt(ARG_SECTION_IMAGE));
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
