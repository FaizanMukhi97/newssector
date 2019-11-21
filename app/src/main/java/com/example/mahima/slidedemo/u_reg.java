package com.example.mahima.slidedemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahima.slidedemo.networkpackage.NetworkClass;
import com.example.mahima.slidedemo.networkpackage.NetworkInterface;
import com.example.mahima.slidedemo.utility.Constans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class u_reg extends AppCompatActivity  implements NetworkInterface {
    Button b1, b2;
    EditText e1, e2, e3, e4, e5, e6, e8;
    Context context;
    String a, b, c, d, e, f, h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_reg);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e4 = (EditText) findViewById(R.id.e4);
        e5 = (EditText) findViewById(R.id.e5);
        e6 = (EditText) findViewById(R.id.e6);
        e8 = (EditText) findViewById(R.id.e8);

        context = this;
    }


    public void submit(View v) {
        String d;
        a = e1.getText().toString();
        b = e2.getText().toString();
        c = e3.getText().toString();
        d = e4.getText().toString();
        e = e5.getText().toString();
        f = e6.getText().toString();

        h = e8.getText().toString();
        String emailPattern = "[a-z0-9._-]+@[a-z]+\\.+[a-z]+";
        String MobilePattern = "[0-9]{10}";

        if (TextUtils.isEmpty(e1.getText())) {
            e1.setError("Name is required ");
        }
        if (TextUtils.isEmpty(e5.getText())) {
            e5.setError("City is required ");
        }
        if (TextUtils.isEmpty(e8.getText())) {
            e8.setError("B_date is required ");
        }
        if (TextUtils.isEmpty(e3.getText())) {
            e3.setError("Password is required ");
        }
        if (TextUtils.isEmpty(e5.getText())) {
            e5.setError("City is required ");
        }

        if (TextUtils.isEmpty(e4.getText())) {
            e4.setError("Address is required ");
        }


        if (!f.matches(MobilePattern)) {
            e6.setError("Enter Valid Mobile No ");
        } else if (!b.matches(emailPattern)) {
            e2.setError("Enter Valid Email Address");

        } else {
            String s = "";
            try {
                s = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("Email_id", "UTF-8") + "=" + URLEncoder.encode(b, "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(c, "UTF-8") + "&" +
                        URLEncoder.encode("Address", "UTF-8") + "=" + URLEncoder.encode(d, "UTF-8") + "&" +
                        URLEncoder.encode("City", "UTF-8") + "=" + URLEncoder.encode(e, "UTF-8") + "&" +
                        URLEncoder.encode("Phone_No", "UTF-8") + "=" + URLEncoder.encode(f, "UTF-8") + "&" +
                        URLEncoder.encode("B_date", "UTF-8") + "=" + URLEncoder.encode(h, "UTF-8");
                Toast.makeText(this, s, Toast.LENGTH_LONG).show();

                Intent i =new Intent(this,u_login.class);
                startActivity(i);
            } catch (Exception e) {
                Toast.makeText(this, "insertion method problem...\n", Toast.LENGTH_LONG).show();
            }
            NetworkClass task = new NetworkClass(this, this);
            task.execute("u_insert.php", s);
        }

    }

    @Override
    public void writeData(String response) {
        //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
        if (response.equals("success")) {
            finish();
        } else if (response.equals("failed")) {
            Toast.makeText(u_reg.this, "Registration Fail", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(u_reg.this, response, Toast.LENGTH_LONG).show();

        }

    }
}

