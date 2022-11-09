package com.example.safetynest;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.emergency.EmergencyNumber;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dashboard extends AppCompatActivity {



    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    Button btn_WN;
    ImageButton btn_GurdianCall,sos;
    AlertDialog.Builder builder;
    FirebaseAuth mAuth;
    int i;
    String name="";
    DatabaseReference reference;
    ArrayList<String> contactlist=new ArrayList<String>();

    public static String PREFS_NAME="MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(Dashboard.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS},
               PackageManager.PERMISSION_GRANTED);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();


        sos=findViewById(R.id.SOS);
        mAuth=FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference().child("PhoneNumbers").child(mAuth.getUid());

        reference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    contactlist.add(ds.getKey());
                    //Toast.makeText(Dashboard.this, ""+ds.getKey(), Toast.LENGTH_SHORT).show();
                }
                    sos.setOnClickListener (new View.OnClickListener () {
                        @Override
                        public void onClick(View v) {



                                for(int i = 0; i<contactlist.size(); i++){
                                    name= contactlist.get(i);
                                    SmsManager mysmsmanager = SmsManager.getDefault();
                                    mysmsmanager.sendTextMessage(name, null, "I'm in trouble...please help me out", null, null);
                                }
                                Toast.makeText(Dashboard.this,"Messge is send to the Guardian",Toast.LENGTH_LONG).show();


                            if(!dataSnapshot.exists()){Toast.makeText(Dashboard.this,"Please add the Guardian",Toast.LENGTH_LONG).show();
                            }

                            //Toast.makeText(Dashboard.this, ""+name, Toast.LENGTH_SHORT).show();




                            //Toast.makeText(MainActivity.this,""+c,Toast.LENGTH_LONG).show();
                            //Intent in = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                            //startActivityForResult (in, RESULT_PICK_CONTACT);


                        }
                    });




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                ; //Don't ignore potential errors!
            }
        });


        btn_GurdianCall=findViewById(R.id.call);
        btn_GurdianCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Dashboard.this,GuardianCall.class);
                startActivity(intent);
            }
        });





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        nav =(NavigationView) findViewById(R.id.navmenu);
        drawerLayout =(DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(Dashboard.this,drawerLayout,toolbar,R.string.open,R.string.close);
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


                        builder = new AlertDialog.Builder(Dashboard.this);
                        builder.setMessage("Do you want to Logout??")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {

                                        SharedPreferences sharedPreferences =getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                                        SharedPreferences.Editor editor=sharedPreferences.edit();
                                        editor.putString("name","false");
                                        editor.apply();

                                        startActivity(new Intent(Dashboard.this,MainActivity.class));
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Logout");
                        alert.show();




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