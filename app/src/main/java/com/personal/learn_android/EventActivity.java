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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.personal.learn_android.model.Event;
import com.personal.learn_android.model.EventPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bervianto Leo P on 02/06/2017.
 */

public class EventActivity extends AppCompatActivity implements OnMapReadyCallback {

    public final static String EXTRA_MESSAGE = "com.personal.learn_android.EVENT_MESSAGE";

    private EventPagerAdapter eventPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    List<Event> listEvent;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    EventFragment eventFragment;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);

        ActionBar ourActionBar = getSupportActionBar();
        if (ourActionBar != null) {
            ourActionBar.setDisplayHomeAsUpEnabled(true);
        }

        this.initial();
    }

    private void setupMapView() {
        if(mViewPager.getVisibility() != View.VISIBLE) {
            mViewPager.setVisibility(View.VISIBLE);
        }
        if(mapFragment.isHidden()) {
            fm.beginTransaction().show(mapFragment).commit();
        }
    }

    private void initial() {
        listEvent = new ArrayList<>();

        Event event1 = new Event("Event 1", "12 Agustus 2017", R.drawable.events, 0.7, 113.2);
        Event event2 = new Event("Event 2", "19 Agustus 2017", R.drawable.events, 0.10, 113.3);
        Event event3 = new Event("Event 3", "13 Agustus 2017", R.drawable.events, 0.9, 113.5);
        Event event4 = new Event("Event 4", "17 Agustus 2017", R.drawable.events, 0.7, 115.3);
        listEvent.add(event1);
        listEvent.add(event2);
        listEvent.add(event3);
        listEvent.add(event4);

        eventFragment = new EventFragment();
        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.list_view_fragment, eventFragment)
                .commit();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment_view);
        fm.beginTransaction().hide(mapFragment).commit();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        eventPagerAdapter = new EventPagerAdapter(getSupportFragmentManager(), listEvent);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(eventPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Event event = listEvent.get(position);
                LatLng place = new LatLng(event.getLatitude(), event.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setVisibility(View.GONE);
        mapFragment.getMapAsync(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EventActivity.this, HomeActivity.class);
        String message = null;
        intent.putExtra(EXTRA_MESSAGE, message);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                String message = null;
                Intent intent = new Intent(EventActivity.this, HomeActivity.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.action_add_event:
                fm.beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .detach(eventFragment).addToBackStack(null)
                        .commit();
                setupMapView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        IconGenerator generator = new IconGenerator(this);
        Random random = new Random();
        for (Event event : listEvent) {
            LatLng place = new LatLng(event.getLatitude(), event.getLongitude());
            generator.setColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            Bitmap icon = generator.makeIcon(event.getEventName());
            mMap.addMarker(new MarkerOptions()
                    .position(place)
                    .title(event.getEventName())
                    .icon(BitmapDescriptorFactory.fromBitmap(icon)));
        }
        Event event = listEvent.get(0);
        LatLng place = new LatLng(event.getLatitude(), event.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 8.0f));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String eventName = marker.getTitle();
                int i = 0;
                for (Event event : listEvent) {
                    if (event.getEventName().equalsIgnoreCase(eventName)) {
                        mViewPager.setCurrentItem(i);
                        break;
                    } else {
                        i++;
                    }
                }
                return true;
            }
        });
    }
}
