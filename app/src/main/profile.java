package com.example.safetynest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {


    FirebaseAuth fAuth;

    FirebaseDatabase database;
    DatabaseReference reference;
    private String PhoneId,userId;
    TextView pname,psurname,paddress,pphone;
    private static final String USERS="Users";
    String email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");
        Intent data=getIntent();
        email = data.getStringExtra("email");



        final TextView  pname=findViewById(R.id.p_name);
        final TextView  psurname=findViewById(R.id.p_surname);
        final TextView  paddress=findViewById(R.id.p_address);
        final TextView  pphone=findViewById(R.id.p_phone);


        database=FirebaseDatabase.getInstance();
        fAuth=FirebaseAuth.getInstance();
        String userid=fAuth.getCurrentUser().getUid().toString();
        reference=database.getReference("Users").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelperClass userHelperClass=snapshot.getValue(UserHelperClass.class);
                pname.setText(userHelperClass.getName());
                psurname.setText(userHelperClass.getSurname());
                paddress.setText(userHelperClass.getAddress());
                pphone.setText(userHelperClass.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}
