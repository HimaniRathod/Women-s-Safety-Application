package com.example.safetynest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.emergency.EmergencyNumber;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    Button sos,btn_WN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        nav =(NavigationView) findViewById(R.id.navmenu);
        drawerLayout =(DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.i_profile:
                    {
                        Intent intent= new Intent(Dashboard.this,profile.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.i_guardian:
                    {
                        startActivity(new Intent(Dashboard.this,Guardian.class));
                        break;
                    }
                    case R.id.i_number:
                    {
                        startActivity(new Intent(Dashboard.this, EmergencyCall.class));
                        break;
                    }
                    case R.id.disc:
                    {
                        startActivity(new Intent(Dashboard.this, Disclaimer.class));
                        break;
                    }

                    case R.id.logout:
                    {
                         FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Dashboard.this,MainActivity.class));
                        break;
                    }
                }
                return true;
            }
        });


    }








    public  void Location(View view){

        startActivity(new Intent(Dashboard.this,
                Location.class));
    }
    public  void women_law(View view){

        startActivity(new Intent(Dashboard.this,Women_and_Law.class));

    }









}
