package com.example.myloginapp;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SubReport extends AppCompatActivity {

    EditText name, email, comment;

    RequestQueue queue;
    //ipaddr :
    final String URL = "http://192.168.0.123/ict602/api.php"; ///Masukkan ip adress

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_report);

        queue = Volley.newRequestQueue(getApplicationContext());

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        comment = (EditText) findViewById(R.id.comment);

        Button button = (Button) findViewById(R.id.Post);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //volley

                makeRequest();
            }
        });
    }

    public void makeRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

            }
        }, errorListener){

            protected Map<String,String> getParams (){
               Map <String, String> params = new HashMap<>();

                params.put("name", name.getText().toString());
                params.put("email", email.getText().toString());
                params.put("comments", comment.getText().toString());

                return params;
            }
        };
        queue.add(stringRequest);

    }

    public Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();


        }
    };
}