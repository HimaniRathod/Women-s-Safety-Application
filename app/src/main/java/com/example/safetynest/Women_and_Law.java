package com.example.safetynest;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;

public class Women_and_Law extends AppCompatActivity {

        Spinner spinner1,spinner2,spinner3,spinner4,spinner5;
        String[] Acid_attack = {"Select Section","Section 326A" , "Section 326B"};
        String[] Laws_regarding_rape = {"Select Section","Section 375" , "Section 376", "Section 376A", "Section 376B", "Section 376C", "Section 376D", "Section 376E" };
        String[] Kidnapping_or_abduction = {"Select Section","Section 363","Section 373"};
        String[] Murder_dowryDeath_abetmentTo_sucide = {"Select Section","Section 302","Section 304B","Section 306"};
        String[] Sexual_harassment = {"Section 354","Section 354A"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_women_and_law);
            spinner1 = findViewById(R.id.spinner1);
            spinner2 = findViewById(R.id.spinner2);
            spinner3 = findViewById(R.id.spinner3);
            spinner4 = findViewById(R.id.spinner4);
            spinner5 = findViewById(R.id.spinner5);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Women_and_Law.this, android.R.layout.simple_spinner_item,Acid_attack);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Women_and_Law.this, android.R.layout.simple_spinner_item,Laws_regarding_rape);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Women_and_Law.this, android.R.layout.simple_spinner_item,Kidnapping_or_abduction);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<String>adapter4 = new ArrayAdapter<String>(Women_and_Law.this, android.R.layout.simple_spinner_item,Murder_dowryDeath_abetmentTo_sucide);
            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(Women_and_Law.this, android.R.layout.simple_spinner_item,Sexual_harassment);

            spinner1.setAdapter(adapter);
            spinner2.setAdapter(adapter2);
            spinner3.setAdapter(adapter3);
            spinner4.setAdapter(adapter4);
            spinner5.setAdapter(adapter5);


            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent , View view, int position, long id) {

                    if (spinner1.getSelectedItem().toString() == "Section 326A") {
                        Intent viewintent = new Intent("android.intent.action.VIEW", Uri.parse("https://devgan.in/ipc/index.php?q=326A&a=1"));
                        startActivity(viewintent);
                    }
                    if (spinner1.getSelectedItem().toString() == "Section 326B") {
                        Intent viewintent = new Intent("android.intent.action.VIEW", Uri.parse("https://devgan.in/ipc/index.php?q=326B&a=1"));
                        startActivity(viewintent);
                    }

                    //  String value = parent.getItemAtPosition(position).toString();
                    //   Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                }






                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }






            });
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(spinner2.getSelectedItem().toString() == "Section 375"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=375&a=1"));
                        startActivity(viewintent);
                    }
                    if(spinner2.getSelectedItem().toString() == "Section 376"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=376&a=1"));
                        startActivity(viewintent);
                    }
                    if(spinner2.getSelectedItem().toString() == "Section 376A"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=376A&a=1"));
                        startActivity(viewintent);
                    }
                    if(spinner2.getSelectedItem().toString() == "Section 376B"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=376B&a=1"));
                        startActivity(viewintent);
                    }
                    if(spinner2.getSelectedItem().toString() == "Section 376C"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=376C&a=1"));
                        startActivity(viewintent);
                    }
                    if(spinner2.getSelectedItem().toString() == "Section 376D"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=376D&a=1"));
                        startActivity(viewintent);
                    }
                    if(spinner2.getSelectedItem().toString() == "Section 376E"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=376E&a=1"));
                        startActivity(viewintent);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(spinner3.getSelectedItem().toString() == "Section 363"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=363&a=1"));
                        startActivity(viewintent);
                    }
                    if(spinner3.getSelectedItem().toString() == "Section 373"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=373&a=1"));
                        startActivity(viewintent);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(spinner4.getSelectedItem().toString() == "Section 302"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=302&a=1"));
                        startActivity(viewintent);
                    }
                    if(spinner4.getSelectedItem().toString() == "Section 304B"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=304B&a=1"));
                        startActivity(viewintent);
                    }
                    if(spinner4.getSelectedItem().toString() == "Section 306"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=306&a=1"));
                        startActivity(viewintent);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(spinner5.getSelectedItem().toString() == "Section 354A"){
                        Intent viewintent = new Intent("android.intent.action.VIEW",Uri.parse("https://devgan.in/ipc/index.php?q=354A&a=1"));
                        startActivity(viewintent);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }
    }
