package com.example.registration.ui.out;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.ForgotPassword;
import com.example.registration.LoginActivity;
import com.example.registration.MainActivity;
import com.example.registration.Nav;
import com.example.registration.R;
import com.example.registration.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class outFragment extends AppCompatActivity{
    EditText lemail, lpwd;
    Button login;
    ProgressBar progressBar1;
    FirebaseAuth fAuth;
    TextView btn,forgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lemail = (EditText) findViewById(R.id.lemail);
        lpwd = (EditText) findViewById(R.id.lpwd);
        forgotpass=(TextView)findViewById(R.id.forgotPassword);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(outFragment.this, ForgotPassword.class));
            }
        });
        btn = (TextView) findViewById(R.id.signup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(outFragment.this, RegisterActivity.class));
            }
        });
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        fAuth = FirebaseAuth.getInstance();
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logemail = lemail.getText().toString();
                String logpwd = lpwd.getText().toString();
                if (TextUtils.isEmpty(logemail)) {
                    lemail.setError("Email is required");
                }
                if (TextUtils.isEmpty(logpwd)) {
                    lpwd.setError("Password is required");
                }
                if (logpwd.length() < 8) {
                    lpwd.setError("Password must be min of 8 characters");
                }
                progressBar1.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(logemail, logpwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(outFragment.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Nav.class));
                        } else {

                            Toast.makeText(outFragment.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar1.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    public void out(MenuItem item) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

