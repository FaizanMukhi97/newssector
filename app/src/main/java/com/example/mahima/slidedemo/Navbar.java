package com.example.mahima.slidedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mahima.slidedemo.networkpackage.NetworkClass;
import com.example.mahima.slidedemo.utility.SharedManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.net.NetworkInterface;
import java.net.URLEncoder;

public class Navbar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, com.example.mahima.slidedemo.networkpackage.NetworkInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frg,new Howtouse());
        ft.commit();

        GetRegistration();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (id == R.id.howtouse) {

            ft.replace(R.id.frg,new Howtouse());
            ft.commit();

        }
        if (id == R.id.enter) {

            SharedManager sm = new SharedManager(this);
            sm.writeData("cat_name","entertainment");//table name
            ft.replace(R.id.frg,new CatFragment());
            ft.commit();

        } else if (id == R.id.sport) {
            SharedManager sm = new SharedManager(this);
            sm.writeData("cat_name","sport");
            ft.replace(R.id.frg,new CatFragment());
            ft.commit();

        } else if (id == R.id.crime) {

            SharedManager sm = new SharedManager(this);
            sm.writeData("cat_name","crime");
            ft.replace(R.id.frg,new CatFragment());
            ft.commit();


        } else if (id == R.id.social) {

            SharedManager sm = new SharedManager(this);
            sm.writeData("cat_name", "social");
            ft.replace(R.id.frg, new CatFragment());
            ft.commit();
        }
            else if (id == R.id.education) {

                SharedManager sm = new SharedManager(this);
                sm.writeData("cat_name", "education");
                ft.replace(R.id.frg, new CatFragment());
                ft.commit();
            }
            else if (id == R.id.politics) {

                SharedManager sm = new SharedManager(this);
                sm.writeData("cat_name", "politics");
                ft.replace(R.id.frg, new CatFragment());
                ft.commit();
            }
            else if (id == R.id.business) {

                SharedManager sm = new SharedManager(this);
                sm.writeData("cat_name", "business");
                ft.replace(R.id.frg, new CatFragment());
                ft.commit();
            }

         else if (id == R.id.contact) {

            ft.replace(R.id.frg,new contactus());
            ft.commit();

        } else if (id == R.id.about) {

            ft.replace(R.id.frg,new aboutus());
            ft.commit();
        }
        else if (id == R.id.profile) {

            ft.replace(R.id.frg,new profile1());
            ft.commit();
        }
        else if (id == R.id.signout) {
            SharedManager sm=new SharedManager(this);
            sm.writeData("login","fail");
            sm.writeData("email","nodata");
            finish();
            Intent i =new Intent(getApplicationContext(),u_login.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void writeData(String response) {}



    void GetRegistration()
        {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener( new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w( "FirebaseInstanceId", "getInstanceId failed", task.getException() );
                                return;
                            }

                            // Get new Instance ID token
                            String token = task.getResult().getToken();
                            SharedManager sh=new SharedManager(Navbar.this);
                            String email=sh.getData("email","def");
                            //Toast.makeText( Navbar.this, token, Toast.LENGTH_SHORT ).show();

                            String s="";

                            try {
                                String Data = URLEncoder.encode( "email", "UTF-8" ) + "=" +
                                        URLEncoder.encode( email, "UTF-8" )+"&"+
                                        URLEncoder.encode( "tokenvalue", "UTF-8" ) + "=" +
                                        URLEncoder.encode( token, "UTF-8" );

                                Toast.makeText( Navbar.this, Data, Toast.LENGTH_SHORT ).show();

                                Request( Data );


                            } catch (Exception ex) {
                                Log.d( "", ex.toString() );
                            }

                        }
                    } );
        }

        void Request(String Data) {
            NetworkClass task = new NetworkClass(this, new com.example.mahima.slidedemo.networkpackage.NetworkInterface() {
                @Override
                public void writeData(String response) {
                    Toast.makeText(Navbar.this,response,Toast.LENGTH_LONG).show();
                }
            });
            task.execute( "inserttoken.php", Data );
        }
    }