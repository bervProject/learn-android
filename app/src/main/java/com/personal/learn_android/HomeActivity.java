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

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    public String name;
    public String guest_text;
    public String event_text;
    public final static int REQUEST_CODE_EVENT = 1;
    public final static int REQUEST_CODE_GUEST = 2;

    @BindView(R.id.button_guest)
    Button guest;
    @BindView(R.id.button_event)
    Button event;
    @BindView(R.id.name_view)
    TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (message != null) {
            name = message;
            nameView.setText(name);
            // Show Dialog isPalindrome
            AlertDialog dialog = new AlertDialog.Builder(this).setTitle(getString(R.string.title_dialog_isPalindrom)).setMessage(isPalindrom(name)).create();
            dialog.show();
        }
    }

    @OnClick(R.id.button_guest)
    public void chooseGuest(View view) {
        Intent intent = new Intent(this, GuestActivity.class);
        startActivityForResult(intent, REQUEST_CODE_GUEST);
    }

    @OnClick(R.id.button_event)
    public void chooseEvent(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivityForResult(intent, REQUEST_CODE_EVENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_GUEST:
                String guestName = data.getStringExtra(GuestActivity.EXTRA_MESSAGE);
                if (guestName != null) {
                    guest_text = guestName;
                    guest.setText(guest_text);
                } else {
                    if (guest_text == null) {
                        guest.setText(getString(R.string.select_guest_button_text));
                    } else {
                        guest.setText(guest_text);
                    }
                }
                break;
            case REQUEST_CODE_EVENT:
                String eventName = data.getStringExtra(EventActivity.EXTRA_MESSAGE);
                if (eventName != null) {
                    event_text = eventName;
                    event.setText(event_text);
                } else {
                    if (event_text == null) {
                        event.setText(getString(R.string.select_event_button_text));
                    } else {
                        event.setText(event_text);
                    }
                }
                break;
        }
    }

    private String isPalindrom(String name) {
        String temp = name.replaceAll("\\s+", "");
        temp = temp.toLowerCase();

        int j = temp.length() - 1;
        int i = 0;
        boolean palindrom = true;
        while (palindrom && i <= j) {
            if (temp.charAt(i) != temp.charAt(j - i)) {
                palindrom = false;
            } else {
                i++;
            }
        }

        if (palindrom) {
            return getString(R.string.palindrome_message);
        } else {
            return getString(R.string.not_palindrome_message);
        }
    }

}
