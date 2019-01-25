package com.example.priteshlad.firebasedemo1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    TextInputLayout email_layout,password_layout;
    TextInputEditText email_et,password_et;

    TextView forgetPass;
    Button login_btn;


    FirebaseAuth auth;
    FirebaseException e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_et = findViewById(R.id.login_email_edittext);
        password_et = findViewById(R.id.login_password_edittext);

        email_layout = findViewById(R.id.email_text_layout);
        password_layout = findViewById(R.id.password_text_layout);

        forgetPass = findViewById(R.id.forgotPassword);

        login_btn = findViewById(R.id.login_btn);

        auth = FirebaseAuth.getInstance();

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),ForgetPassword.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email_et.getText().toString().equals("") && password_et.getText().toString().equals("")){
                    email_layout.setError("It can't be empty");
                    Toast.makeText(getApplicationContext(),"Empty value is not allowed",Toast.LENGTH_SHORT).show();

                } else {
                    auth.signInWithEmailAndPassword(email_et.getText().toString(),password_et.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT);
                                e = (FirebaseException) task.getException();
                                Log.e("dataFail","Error in login",e);
                                finish();
                                Intent intent = new Intent(getApplicationContext(),Profile.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(),"User not found",Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }

            }
        });
    }

    public void register1(View view)
    {
        Intent intent = new Intent();
        intent.setClass(this,Register.class);
        startActivity(intent);
    }
}

