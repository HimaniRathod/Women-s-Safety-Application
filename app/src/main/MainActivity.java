package com.example.safetynest;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashScreen.setKeepOnScreenCondition(() -> true );
        new Handler().postDelayed(new Runnable() {
            public void run() {




                    Intent i = new Intent(MainActivity.this,Loginpage.class);
                    startActivity(i);
                    finish();



            }
        },1000);



    }



}