package com.example.safetynest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Loginpage extends AppCompatActivity {

    public static String PREFS_NAME="MyPrefsFile";

    TextView tv,forgot;
    EditText Email,Password;
    Button btnlogin;
    String emailpattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}+";
    String MobilePattern = "[0-9]{10}";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    //forgoogle signin
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        //forgoogl signin
        googleBtn = findViewById(R.id.google_btn);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){


            navigateToSecondActivity();

        }



        checklogin();



        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        Email=findViewById(R.id.username);
        Password=findViewById(R.id.password);
        progressDialog=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();






        btnlogin =findViewById(R.id.login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                performlogin();



            }
        });
        TextView tv=(TextView)findViewById(R.id.Register);
        tv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //perform your action here

                startActivity(new Intent(Loginpage.this,Register.class));
            }
        });

        TextView forgot=findViewById(R.id.fp);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Loginpage.this,Forgotpassword.class));
            }
        });

    }


        //for googlesignin

    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                SharedPreferences sharedPreferences =getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("name","true");
                editor.apply();
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }

    void navigateToSecondActivity(){

        SharedPreferences sharedPreferences =getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name","true");
        editor.apply();
        SendUSerToNextActivity();
    }
    //For one time appear Login Screen
    private  void checklogin(){

        SharedPreferences sharedPreferences =getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String check = sharedPreferences.getString("name"," ");
        if(check.equals("true")){
            Intent intent=new Intent(getApplicationContext(),Dashboard.class);
            startActivity(intent);
            finish();
        }

    }

    private void performlogin() {

        String email=Email.getText().toString();
        String password =Password.getText().toString();


        if(email.isEmpty()){
            Email.setError("Please Enter Email");
        }
        else if(!email.matches(emailpattern)){
            Email.setError("Enter Proper Email");
        }
        else if(password.isEmpty()){
            Password.setError("Please Enter Password");
        }

        else{
            progressDialog.setMessage("Please wait while Login....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();



            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        //for one time login
                        SharedPreferences sharedPreferences =getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("name","true");
                        editor.apply();

                        progressDialog.dismiss();
                       SendUSerToNextActivity();
                       Toast.makeText(Loginpage.this,"Login is Successfull",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(Loginpage.this,"Enter Corret Data",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
    private void SendUSerToNextActivity() {

        Intent intent = new Intent(Loginpage.this,Dashboard.class);
        startActivity(intent);
        finish();

    }

}
