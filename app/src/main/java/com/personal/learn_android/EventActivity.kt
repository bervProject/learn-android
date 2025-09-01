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

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ui.IconGenerator
import com.personal.learn_android.adapter.EventPagerAdapter
import com.personal.learn_android.databinding.ActivityEventBinding
import com.personal.learn_android.model.Event
import io.realm.Realm
import java.util.*

/**
 * Created by Bervianto Leo P on 02/06/2017.
 */
class EventActivity : AppCompatActivity(), OnMapReadyCallback {
    private val listEvent: MutableList<Event> = mutableListOf()
    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var eventFragment: EventFragment
    private lateinit var fm: FragmentManager
    private lateinit var realm: Realm
    private lateinit var binding: ActivityEventBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.eventToolbar)
        val ourActionBar = supportActionBar
        ourActionBar?.setDisplayHomeAsUpEnabled(true)
        realm = Realm.getDefaultInstance()
        initial()
        
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackPress()
            }
        })
    }

    private fun setupMapView() {
        if (binding.container.visibility != View.VISIBLE) {
            binding.container.visibility = View.VISIBLE
        }
        if (mapFragment.isHidden) {
            fm.beginTransaction().show(mapFragment).commit()
        }
    }

    private fun initial() {
        listEvent.addAll(realm.where(Event::class.java).findAll())
        if (listEvent.isEmpty()) {
            val event1 = Event("Event 1", "12 Agustus 2017", R.drawable.events, 0.7, 113.2)
            val event2 = Event("Event 2", "19 Agustus 2017", R.drawable.events, 0.10, 113.3)
            val event3 = Event("Event 3", "13 Agustus 2017", R.drawable.events, 0.9, 113.5)
            val event4 = Event("Event 4", "17 Agustus 2017", R.drawable.events, 0.7, 115.3)
            listEvent.add(event1)
            listEvent.add(event2)
            listEvent.add(event3)
            listEvent.add(event4)
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(listEvent)
            realm.commitTransaction()
        }
        eventFragment = EventFragment()
        fm = supportFragmentManager
        fm.beginTransaction().add(R.id.list_view_fragment, eventFragment).commit()
        mapFragment = supportFragmentManager
                .findFragmentById(R.id.map_fragment_view) as SupportMapFragment
        fm.beginTransaction().hide(mapFragment).commit()

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        val eventPagerAdapter = EventPagerAdapter(supportFragmentManager, listEvent)
        binding.container.adapter = eventPagerAdapter
        binding.container.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                val event = listEvent[position]
                val place = LatLng(event.latitude, event.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        binding.container.visibility = View.GONE
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_event, menu)
        return true
    }

    private fun handleBackPress() {
        val intent = Intent(this@EventActivity, HomeActivity::class.java)
        val message: String? = null
        intent.putExtra(EXTRA_MESSAGE, message)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val message: String? = null
                val intent = Intent(this@EventActivity, HomeActivity::class.java)
                intent.putExtra(EXTRA_MESSAGE, message)
                setResult(Activity.RESULT_OK, intent)
                finish()
                true
            }
            R.id.action_add_event -> {
                fm.beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .detach(eventFragment).addToBackStack(null)
                        .commit()
                setupMapView()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val generator = IconGenerator(this)
        val random = Random()
        for (event in listEvent) {
            val place = LatLng(event.latitude, event.longitude)
            generator.setColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)))
            val icon = generator.makeIcon(event.eventName)
            mMap.addMarker(MarkerOptions()
                    .position(place)
                    .title(event.eventName)
                    .icon(BitmapDescriptorFactory.fromBitmap(icon)))
        }
        val event = listEvent[0]
        val place = LatLng(event.latitude, event.longitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 8.0f))
        mMap.setOnMarkerClickListener { marker: Marker ->
            val eventName = marker.title
            var i = 0
            for (event1 in listEvent) {
                if (event1.eventName.equals(eventName, ignoreCase = true)) {
                    binding.container.currentItem = i
                    break
                } else {
                    i++
                }
            }
            true
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "com.personal.learn_android.EVENT_MESSAGE"
    }
}
