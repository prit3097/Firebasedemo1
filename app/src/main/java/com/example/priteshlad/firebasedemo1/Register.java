package com.example.priteshlad.firebasedemo1;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    TextInputLayout Register_name_text_layout,Register_email_text_layout,Register_password_text_layout,Register_c_password_text_layout;
    TextInputEditText name,email,password,cpassword;

    Button register;

    FirebaseAuth auth;
    FirebaseException e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register_name_text_layout = findViewById(R.id.Register_name_text_layout);
        Register_email_text_layout = findViewById(R.id.Register_email_text_layout);
        Register_password_text_layout = findViewById(R.id.Register_password_text_layout);
        Register_c_password_text_layout = findViewById(R.id.Register_c_password_text_layout);

        name = findViewById(R.id.Register_edittext_name);
        email = findViewById(R.id.Register_edittext_email);
        password = findViewById(R.id.Register_edittext_password);
        cpassword = findViewById(R.id.Register_edittext_c_password);

        register = findViewById(R.id.register_btn);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  createUser(v);
            }
        });

    }



    public void createUser(View view){

        String id = email.getText().toString();
        String pass = password.getText().toString();
        String cPass = cpassword.getText().toString();


        if (id.equals("")){
            Register_email_text_layout.setError("It can't be empty");
            //Toast.makeText(getApplicationContext(),"Email and Password can't be empty",Toast.LENGTH_SHORT).show();
        } else if (pass.equals("")){
            Register_password_text_layout.setError("It can't be empty");
        } else if (cPass.equals("")){
            Register_c_password_text_layout.setError("It can't be empty");
        } else if (!pass.equals(cPass)){
            Toast.makeText(getApplicationContext(),"Password did not matched",Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(id,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"User is created",Toast.LENGTH_LONG).show();
                        finish();
                        Intent intent = new Intent(getApplicationContext(),Profile.class);
                        startActivity(intent);
                    } else {
                        e =  (FirebaseException) task.getException();
                        Toast.makeText(getApplicationContext(),"User is not created",Toast.LENGTH_LONG).show();

                        Log.e("dataFail","data insertion failed",e);
                    }
                }
            });
        }
    }

    public void register(View view)
    {
        Intent intent = new Intent();
        intent.setClass(this,Login.class);
        startActivity(intent);
    }
}
