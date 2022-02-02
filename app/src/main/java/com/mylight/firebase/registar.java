package com.mylight.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registar extends AppCompatActivity {
    EditText registerFullName,registerEmail,registerPassword,registerConfPass;
    Button registerUserBtn,gotoLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        registerFullName=findViewById(R.id.registarFullname);
        registerEmail=findViewById(R.id.registarEmail);
        registerPassword=findViewById(R.id.registarPassword);
        registerConfPass=findViewById(R.id.confmPassword);
        registerUserBtn=findViewById(R.id.registarBtn);
        gotoLogin=findViewById(R.id.gotoLogin);

        fAuth = FirebaseAuth.getInstance();

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();
            }
        });

        registerUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = registerFullName.getText().toString();
                String Email = registerEmail.getText().toString();
                String Password = registerPassword.getText().toString();
                String confmPassword = registerConfPass.getText().toString();

                if (fullName.isEmpty()){
                    registerFullName.setError("Full name is required");
                    return;
                }
                if (Email.isEmpty()){
                    registerEmail.setError("Email is required");
                    return;
                }
                if (Password.isEmpty()){
                    registerPassword.setError("Password is required");
                    return;
                }
                if (confmPassword.isEmpty()){
                    registerConfPass.setError("Password is required");
                    return;
                }

                if (!Password.equals(confmPassword)){
                    registerConfPass.setError("Password is not mach");
                    return;
                }

                Toast.makeText(registar.this,"Data Validated",Toast.LENGTH_SHORT).show();

                fAuth.createUserWithEmailAndPassword(Email,Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registar.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}