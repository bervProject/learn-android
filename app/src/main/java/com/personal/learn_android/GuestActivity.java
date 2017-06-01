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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.personal.learn_android.http.HttpService;
import com.personal.learn_android.model.Guest;
import com.personal.learn_android.model.GuestAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuestActivity extends AppCompatActivity {

    @BindView(R.id.gridview)
    GridView gridView;

    SwipeRefreshLayout swipe;

    public final static String EXTRA_MESSAGE = "com.personal.learn_android.GUEST_MESSAGE";
    private String TAG = GuestActivity.class.getSimpleName();

    private HttpService service;
    private GuestAdapter guestAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        ButterKnife.bind(this);
        ActionBar ourActionBar = getSupportActionBar();
        if(ourActionBar != null) {
            ourActionBar.setDisplayHomeAsUpEnabled(true);
        }
        realm = Realm.getDefaultInstance();
        this.setupService();
        this.getData();
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_guest);
        swipe.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void setupService() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HttpService.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        service = retrofit.create(HttpService.class);
    }

    private String testTanggal(String tanggal) {
        String date = tanggal.split("-")[2];
        Integer day = Integer.valueOf(date);
        if ((day % 2) == 0 && (day % 3) == 0) {
            return "iOS";
        } else if (day % 2 == 0) {
            return "blackberry";
        } else if (day % 3 == 0) {
            return "android";
        } else {
            return "feature phone";
        }
    }

    private String isPrime(String tanggal) {
        String date = tanggal.split("-")[1];
        Integer day = Integer.valueOf(date);
        if (day < 2) return "not prime";
        if (day == 2) return "prime";
        if (day % 2 == 0) return "not prime";
        for (int i = 3; i * i <= day; i += 2)
            if (day % i == 0) return "not prime";
        return "prime";
    }

    private void getData() {
        service.listGuests().enqueue(new Callback<List<Guest>>() {
            @Override
            public void onResponse(Call<List<Guest>> call, Response<List<Guest>> response) {
                if (response.isSuccessful()) {
                    List<Guest> guestList = response.body();
                    // Update Realm Guest Object
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(guestList);
                    realm.commitTransaction();
                    guestAdapter = new GuestAdapter(GuestActivity.this, guestList);
                    gridView.setAdapter(guestAdapter);
                    swipe.setRefreshing(false);
                } else {
                    // handle error
                }
            }

            @Override
            public void onFailure(Call<List<Guest>> call, Throwable t) {
                t.printStackTrace();
                // Using already downloaded List
                List<Guest> guestList = realm.where(Guest.class).findAll();
                if (guestList != null) {
                    guestAdapter = new GuestAdapter(GuestActivity.this, guestList);
                    gridView.setAdapter(guestAdapter);
                }
                swipe.setRefreshing(false);
            }

        });
    }

    @OnItemClick(R.id.gridview)
    void onItemClicked(int position) {
        Guest guest = guestAdapter.getItem(position);
        if (guest != null) {
            String message = guest.getName();
            Toast.makeText(getApplicationContext(), testTanggal(guest.getBirthdate()), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(GuestActivity.this, HomeActivity.class);
            intent.putExtra(EXTRA_MESSAGE, message);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GuestActivity.this, HomeActivity.class);
        String message = null;
        intent.putExtra(EXTRA_MESSAGE, message);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
