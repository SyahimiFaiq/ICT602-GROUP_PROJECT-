package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Homescreen extends AppCompatActivity implements View.OnClickListener{

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signOutBtn;

    CardView openview;

    CardView openreport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        signOutBtn = findViewById(R.id.signout);
        Button about = findViewById(R.id.about);
        about.setOnClickListener(this);


        openview = (CardView) findViewById(R.id.buttonView);
        openview.setOnClickListener(this);

        openreport= (CardView) findViewById(R.id.buttonReport);
        openreport.setOnClickListener(this);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signOut();
            }
        });

    }


    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.buttonView:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ViewNews.class);
                startActivity(intent);
                break;

            case R.id.buttonReport:
                Intent intent2 = new Intent();
                intent2.setClass(getApplicationContext(), SubReport.class);
                startActivity(intent2);
                break;

            case R.id.about:
                Intent intent3 = new Intent();
                Intent aboutIntent = new Intent();
                intent3.setClass(getApplicationContext(), AboutUs.class);
                startActivity(intent3);
                break;
        }
    }


    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(Homescreen.this,MainActivity.class));
            }
        });
    }
}