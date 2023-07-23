package com.example.myloginapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewNews extends AppCompatActivity {


    TextView news_titleTextView;
    TextView news_dateTextView;
    RequestQueue requestQueue;
    final String SERVER_URL = "http://192.168.0.123/ict602/api.php"; //masuk ip


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);


            news_titleTextView = findViewById(R.id.news_titleTextView);
            news_dateTextView = findViewById(R.id.news_dateTextView);

            requestQueue = Volley.newRequestQueue(this);
            fetchDataFromServer();
        }

        private void fetchDataFromServer () {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, SERVER_URL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String news_title = response.getString("news_title");
                                String news_date = response.getString("news_date");

                                news_titleTextView.setText("Title: " + news_title);
                                news_dateTextView.setText("Date: " + news_date);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

            requestQueue.add(jsonObjectRequest);
        }
    }