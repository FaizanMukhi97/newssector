package com.example.mahima.slidedemo;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahima.slidedemo.dbhelper.DBHelper;
import com.example.mahima.slidedemo.networkpackage.NetworkClass;
import com.example.mahima.slidedemo.networkpackage.NetworkInterface;
import com.example.mahima.slidedemo.utility.Constans;
import com.example.mahima.slidedemo.utility.SharedManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class u_login extends AppCompatActivity implements NetworkInterface {
    EditText email, pass;
    //String a,b;
    Button v;
    Context c;
    SQLiteDatabase db;
        String table_name = "news_counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        c = this;

        SharedManager sm = new SharedManager(u_login.this);
        String data = sm.getData("login", "fail");

        DBHelper helper = new DBHelper(this);
        boolean n = helper.insertCat("temp","0","0");
        Toast.makeText(c, n+" insert", Toast.LENGTH_SHORT).show();
        int n1 = helper.deleteCat("temp");
        Toast.makeText(c, n1+" delete", Toast.LENGTH_SHORT).show();

        if (data.equals("success")) {
            Intent i = new Intent(u_login.this, Navbar.class);
            startActivity(i);
            finish();
        }
    }

    String a = "";

    public void login(View view) {

        a = email.getText().toString();


        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (a.matches(emailPattern)) {
            Toast.makeText(getApplicationContext(), " valid email address", Toast.LENGTH_SHORT).show();
        }
        String b = pass.getText().toString();
        if (a.length() > 0 && b.length() >= 4) {
            String s = "";
            try {
                s = URLEncoder.encode("Email_id", "UTF-8") + "=" +
                        URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" +
                        URLEncoder.encode(b, "UTF-8");

            } catch (Exception j) {
                Toast.makeText(this, "insertion method prb...\n" + j.toString(), Toast.LENGTH_LONG).show();
            }
            NetworkClass task = new NetworkClass(this, this);
            task.execute("login.php", s);
        }
    }

    public void writeData(String response) {
        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
        if (response.equals("success")) {
            SharedManager sm = new SharedManager(getApplicationContext());
            sm.writeData("login", "success");
            sm.writeData("email", a);
            Intent i = new Intent(u_login.this, Navbar.class);
            startActivity(i);
        } else if (response.equals("fail")) {
            Toast.makeText(u_login.this, "login fail", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(u_login.this, response, Toast.LENGTH_LONG).show();
        }
    }

    public void newuser(View v)
    {
        Intent i=new Intent(this,u_reg.class);
        startActivity(i);
    }
}