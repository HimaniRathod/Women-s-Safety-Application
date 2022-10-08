package com.example.safetynest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText Name,Surname,Address,Phone,Email,Password;
    Button btnregister;
    String emailpattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}+";
    String MobilePattern = "[0-9]{10}";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

     //fore database
     FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + getString(R.string.Registration) + "</font>"));




        Email=findViewById(R.id.email);
        Name=findViewById(R.id.name);
        Surname=findViewById(R.id.surname);
        Address=findViewById(R.id.address);
        Phone=findViewById(R.id.phone);
        Password=findViewById(R.id.password);
        progressDialog=new ProgressDialog(this);
        btnregister= findViewById(R.id.Register);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();






        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                performAuth();


            }


        });
    }
    private void insertData()
    {

        String name= Name.getText().toString();
        String surname = Surname.getText().toString();
        String email=Email.getText().toString();
        String password =Password.getText().toString();
        String phone = Phone.getText().toString();
        String address= Address.getText().toString();

        rootNode= FirebaseDatabase.getInstance();
        reference=rootNode.getReference("Users");


        //get all values


        UserHelperClass helperClass = new UserHelperClass(name,surname,address,phone,email,password);

        reference.child(phone).setValue(helperClass);





    }

    private void performAuth() {

        String email=Email.getText().toString();
        String password =Password.getText().toString();
        String phone = Phone.getText().toString();


        if(phone.isEmpty()){

            Phone.setError("Please Enter Password");
        }
        else if(!phone.matches(MobilePattern)){
            Phone.setError("Enter valid Phone");
        }
        else if(email.isEmpty()){
            Email.setError("Please Enter Email");
        }
        else if(!email.matches(emailpattern)){
            Email.setError("Enter Proper Email");
        }
        else if(password.isEmpty()){
            Password.setError("please Enter Password ");
        }


        else if(password.length()<8){
            Password.setError("Password must consist of 8 characters");
        }
        else{
            progressDialog.setMessage("Please wait while Registration....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();



            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        insertData();
                        SendUSerToNextActivity();
                        Toast.makeText(Register.this,"Registration is Successfull",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(Register.this,"This Id already exists",Toast.LENGTH_SHORT).show();
                    }

                }


            });
        }

    }
    private void SendUSerToNextActivity() {

        Intent intent = new Intent(Register.this,Loginpage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



}

