package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.ktx.Firebase;

public class ForgotPassword extends AppCompatActivity {
private Button fpwd;
private EditText femail;
private TextView flogin;
private String email;
private FirebaseAuth auth1;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        auth1=FirebaseAuth.getInstance();
        femail=findViewById(R.id.femail);
        fpwd=findViewById(R.id.fpwd);
        flogin=(TextView)findViewById(R.id.flogin);
        flogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPassword.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        fpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void validateData() {
        email=femail.getText().toString();
        if(email.isEmpty()){
            femail.setError(("Required"));
        }
        else{
            forgetPass();
        }
    }

    private void forgetPass() {
        auth1.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPassword.this, "Check your Email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPassword.this,LoginActivity.class));
                        }else {
                            Toast.makeText(ForgotPassword.this, "Error", Integer.parseInt(task.getException().getMessage())).show();
                        }
                    }
                });
    }
}