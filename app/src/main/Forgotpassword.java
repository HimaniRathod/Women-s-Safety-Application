package com.example.safetynest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpassword extends AppCompatActivity {


    Button forgotbtn;
    EditText txtemail;
    String email;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        TextView tv=findViewById(R.id.login);


        auth = FirebaseAuth.getInstance();
        txtemail=findViewById(R.id.username);
        forgotbtn = findViewById(R.id.send);

        forgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatedata();
            }
        });


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Forgotpassword.this,MainActivity.class));
            }
        });
    }

    private  void validatedata(){
        email = txtemail.getText().toString();
        if (email.isEmpty()){
            txtemail.setError("Please Enter Email");
        }
        else{
            forgotpass();
        }
    }

    private void forgotpass(){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Forgotpassword.this, "Please check your email account", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Forgotpassword.this, "Email not found, please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}