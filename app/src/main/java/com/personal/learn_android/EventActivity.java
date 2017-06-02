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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class EventActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.personal.learn_android.EVENT_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar eventToolbar = (Toolbar) findViewById(R.id.event_toolbar);
        setSupportActionBar(eventToolbar);
        ActionBar eventActionBar = getSupportActionBar();
        if (eventActionBar != null) {
            eventActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        String message = null;
        Intent intent = new Intent(EventActivity.this, HomeActivity.class);
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
        }
        return true;
    }

}
