package com.example.safetynest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Guardian extends AppCompatActivity {

    //fore database
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    //for authentiction
    FirebaseAuth mAuth;
    //Recycleview
    RecyclerView rcv;
    myadapter adap;
    long count;
    ArrayList<Model> contactlist=new ArrayList<>();

    private static final int RESULT_PICK_CONTACT =1;
    private Button select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian);
        ActivityCompat.requestPermissions(Guardian.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS},
                PackageManager.PERMISSION_GRANTED);


        mAuth=FirebaseAuth.getInstance();

        //count the child of the rootnode
        rootNode= FirebaseDatabase.getInstance();
        reference=rootNode.getReference().child("PhoneNumbers").child(mAuth.getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    count= dataSnapshot.getChildrenCount();}


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       //firebase to recycleview
        rcv = (RecyclerView) findViewById(R.id.recview);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PhoneNumbers").child(mAuth.getUid()), Model.class)
                        .build();

        // rcv.setAdapter(adapter);
        adap=new myadapter(options);
        rcv.setAdapter(adap);
       // Toast.makeText(this,""+rcv.getAdapter().getItemCount(),Toast.LENGTH_LONG).show();

        select = findViewById (R.id.select);

        select.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult (in, RESULT_PICK_CONTACT);

            }
        });




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

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(this, "Failed To pick contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {

            String Name = null;
            String phoneNo = null;
            Uri uri = data.getData ();
            cursor = getContentResolver ().query (uri, null, null,null,null);
            cursor.moveToNext ();
            int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);
            int nameIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString (phoneIndex);
            Name=cursor.getString(nameIndex);


            if(count<5) {

                //contactlist.add(new Model(Name,phoneNo));
                //adapter.notifyItemInserted(contactlist.size() - 1);

                //rcv.scrollToPosition(contactlist.size() - 1);

                //insert into database

                rootNode= FirebaseDatabase.getInstance();
                reference=rootNode.getReference("PhoneNumbers");


                //get all values
                Model helperClass = new Model(Name,phoneNo);
                reference.child(mAuth.getUid()).child(phoneNo).setValue(helperClass);
                Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();


                adap.startListening();
                rcv.setAdapter(adap);


            }

            else{
                Toast.makeText(this, "You can add upto 5 guardians", Toast.LENGTH_SHORT).show();
                adap.startListening();
                rcv.setAdapter(adap);

            }

        } catch (Exception e) {
            e.printStackTrace ();
        }

    }




}
