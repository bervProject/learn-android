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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.item_event_map.*

/**
 * Created by Bervianto Leo P on 03/06/2017.
 */
class EventPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val bundle = arguments
        val rootView = inflater.inflate(R.layout.item_event_map, container, false)
        if (bundle != null) {
            event_map_text.text = bundle.getString(ARG_SECTION_NAME)
            event_map_image.setImageResource(bundle.getInt(ARG_SECTION_IMAGE))
        }
        return rootView
    }


    companion object {
        private const val ARG_SECTION_IMAGE = "section_image"
        private const val ARG_SECTION_NAME = "section_name"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(image: Int?, name: String?): EventPagerFragment {
            val fragment = EventPagerFragment()
            val args = Bundle()
            args.putString(ARG_SECTION_NAME, name)
            args.putInt(ARG_SECTION_IMAGE, image!!)
            fragment.arguments = args
            return fragment
        }
    }
}