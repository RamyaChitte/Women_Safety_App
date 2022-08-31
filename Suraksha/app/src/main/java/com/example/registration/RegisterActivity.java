package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText rusr, remail, rpwd, rcpwd;
    Button register;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rusr = (EditText) findViewById(R.id.rusr);
        remail = (EditText) findViewById(R.id.lemail);
        rpwd = (EditText) findViewById(R.id.lpwd);
        rcpwd = (EditText) findViewById(R.id.rcpwd);
        btn = (TextView) findViewById(R.id.acc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        //if(fAuth.getCurrentUser()!=null)
       // {
           // finish();
        //}
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = rusr.getText().toString();
                String email = remail.getText().toString();
                String pwd = rpwd.getText().toString();
                String cpwd = rcpwd.getText().toString();

                if (TextUtils.isEmpty(uname)) {
                    rusr.setError("Username is required");
                }
                if (TextUtils.isEmpty(email)) {
                    remail.setError("Email is required");
                }
                if (TextUtils.isEmpty(pwd)) {
                    rpwd.setError("Password is required");
                }
                if (TextUtils.isEmpty(cpwd)) {
                    rcpwd.setError("Password is required");
                }
                if (pwd.length() < 8) {
                    rpwd.setError("Password must be min of 8 characters");
                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }
}
