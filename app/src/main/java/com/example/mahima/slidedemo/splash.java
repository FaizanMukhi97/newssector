package com.example.mahima.slidedemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class splash extends AppCompatActivity implements Runnable {
    Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        h=new Handler();
       h.postDelayed(this,2000);
    }
    public void run() {
        Intent i;
        i=new Intent(this,u_login.class);
        finish();
        startActivity(i);
    }
}
