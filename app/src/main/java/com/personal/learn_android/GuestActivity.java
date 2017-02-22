package com.personal.learn_android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class GuestActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.personal.learn_android.GUEST_MESSAGE";
    //Web api url
    public static final String DATA_URL = "http://dry-sierra-6832.herokuapp.com/api/people";

    private String TAG = GuestActivity.class.getSimpleName();
    private GridView gridView;
    ArrayList<Guest> guestList;

    static String NAMA = "nama";
    static String BIRTHDATE = "birthdate";
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        guestList = new ArrayList<>();
        gridView = (GridView) findViewById(R.id.gridview);

        new GetGuests().execute();
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


    private class GetGuests extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(GuestActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = DATA_URL;
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray guests = new JSONArray(jsonStr);
                    //Log.e(TAG,"Found : " + guests.length());
                    // looping through All Contacts

                    for (int i = 0; i < guests.length(); i++) {
                        JSONObject c = guests.getJSONObject(i);
                        int id = c.getInt("id");
                        String name = c.getString("name");
                        String birthdate = c.getString("birthdate");

                        Guest guest = new Guest(id,R.drawable.face,name,birthdate);

                        // adding contact to contact list
                        guestList.add(guest);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Create the adapter to convert the array to views
            final GuestAdapter adapter = new GuestAdapter(GuestActivity.this, guestList);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    String message= adapter.getItem(position).getName();
                    Toast.makeText(getApplicationContext(), testTanggal(adapter.getItem(position).getBirthdate()), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GuestActivity.this,HomeActivity.class);
                    intent.putExtra(EXTRA_MESSAGE,message);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            });
        }
    }

}
