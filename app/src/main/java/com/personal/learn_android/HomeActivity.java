package com.personal.learn_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE_GUEST = "com.personal.learn_android.GUEST_MESSAGE";
    public final static String EXTRA_MESSAGE_EVENT = "com.personal.learn_android.EVENT_MESSAGE";
    public String name;
    public String guest_button;
    public String event_button;

    public final static int REQUEST_CODE_EVENT = 1;
    public final static int REQUEST_CODE_GUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (message!= null) {
            TextView nameView = (TextView) findViewById(R.id.name_view);
            name = message;
            nameView.setText(name);
        } else {
            //TextView nameView = (TextView) findViewById(R.id.name_view);
            //nameView.setText(name);
        }

    }

    public void chooseGuest(View view) {
        Intent intent = new Intent(this, GuestActivity.class);
        startActivityForResult(intent,REQUEST_CODE_GUEST);
    }

    public void chooseEvent(View view){
        Intent intent = new Intent(this, EventActivity.class);
        startActivityForResult(intent,REQUEST_CODE_EVENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case REQUEST_CODE_GUEST:
                String guestName = data.getStringExtra(GuestActivity.EXTRA_MESSAGE);
                if (guestName != null) {
                    Button guest = (Button) findViewById(R.id.button_guest);
                    guest_button = guestName;
                    guest.setText(guest_button);
                } else {
                    Button guest = (Button) findViewById(R.id.button_guest);
                    if (guest_button == null) {
                        guest.setText("Pilih Event");
                    } else {
                        guest.setText(guest_button);
                    }
                }
                break;
            case REQUEST_CODE_EVENT:
                String eventName = data.getStringExtra(EventActivity.EXTRA_MESSAGE);
                if (eventName != null) {
                    Button event = (Button) findViewById(R.id.button_event);
                    event_button = eventName;
                    event.setText(event_button);
                } else {
                    Button event = (Button) findViewById(R.id.button_event);
                    if (event_button == null) {
                        event.setText("Pilih Event");
                    } else {
                        event.setText(event_button);
                    }
                }
                break;
        }
    }
}
