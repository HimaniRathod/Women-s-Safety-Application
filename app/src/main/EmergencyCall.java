package com.example.safetynest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class EmergencyCall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);
        setTitle("EmergencyCall");

        }
        public void WHcall(View view) {
            Intent intent  = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:181"));
            startActivity(intent);}

            public void Police_call(View view) {
                Intent intent2  = new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:100"));
                startActivity(intent2);
            }

    public void ambulance_call(View view) {
        Intent intent3  = new Intent(Intent.ACTION_DIAL);
        intent3.setData(Uri.parse("tel:108"));
        startActivity(intent3);
    }
    }

