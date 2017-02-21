package com.personal.learn_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class GuestActivity extends AppCompatActivity {
    JSONObject jsonobject;
    JSONArray jsonarray;

    static String NAMA = "nama";
    static String BIRTHDATE = "birthdate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GuestActivity.this, testTanggal("2017-02-3"),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public String testTanggal(String tanggal) {
        String date = tanggal.split("-")[2];
        Integer day = Integer.valueOf(date);
        if ((day % 2) == 0 && (day %3) == 0) {
            return "iOS";
        } else if (day % 2 == 0) {
            return "blackberry";
        } else if (day % 3 == 0) {
            return "android";
        } else {
            return "feature phone";
        }
    }
}
