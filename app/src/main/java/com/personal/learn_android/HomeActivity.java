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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView nameView = (TextView) findViewById(R.id.name_view);
        nameView.setText(message);

        String eventName = intent.getStringExtra(EventActivity.EXTRA_MESSAGE);
        if (eventName != null) {
            Button event = (Button) findViewById(R.id.button_event);
            event.setText(eventName);
        }
    }

    public void chooseGuest(View view) {
        Intent intent = new Intent(this, GuestActivity.class);
        startActivity(intent);
    }

    public void chooseEvent(View view){
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }
}
