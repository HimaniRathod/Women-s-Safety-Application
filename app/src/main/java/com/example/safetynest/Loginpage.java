package com.example.safetynest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Loginpage extends AppCompatActivity {

    public static String PREFS_NAME="MyPrefsFile";
    private Button signIn;
    public EditText u;
    public EditText p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        u =findViewById(R.id.username);
        p =findViewById(R.id.password);

        signIn =findViewById(R.id.login);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences(Loginpage.PREFS_NAME,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                editor.putBoolean("hasLoggedIn",true);
                editor.commit();


                startActivity(new Intent(Loginpage.this,Dashboard.class));

                finish();


            }
            public void register(View view){
                startActivity(new Intent(Loginpage.this,Register.class));

            }



        });
    }
}