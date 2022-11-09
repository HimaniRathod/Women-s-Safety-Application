package com.example.safetynest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class GuardianCall extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    RecyclerView rcv;
    myadapter2 adap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_call);

        mAuth=FirebaseAuth.getInstance();
        //firebase to recycleview
        rcv = (RecyclerView) findViewById(R.id.recview2);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PhoneNumbers").child(mAuth.getUid()), Model.class)
                        .build();

        // rcv.setAdapter(adapter);
        adap=new myadapter2(options);
        rcv.setAdapter(adap);


    }

    @Override
    protected void onStart(){
        super.onStart();
        adap.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        adap.stopListening();
    }
}