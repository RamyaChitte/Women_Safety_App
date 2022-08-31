package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.registration.ui.feed.feeds;
import com.example.registration.ui.home.HomeFragment;

public class ContactActivity extends AppCompatActivity {
    ImageView p1,p2,p3,p4;
    //Button back;
    private static final int CONTACT_PERMISSION_CODE=1;
    private static final int CONTACT_PICK_CODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        p1=(ImageView) findViewById(R.id.get01);
        p2=(ImageView) findViewById(R.id.p2);
        p3=(ImageView) findViewById(R.id.p3);
        p4=(ImageView) findViewById(R.id.p4);
        /*back=(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContactActivity.this, HomeFragment.class);
                startActivity(intent);
            }
        });*/
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContactActivity.this,NameActivity.class);
                startActivity(intent);

            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContactActivity.this,name1.class);
                startActivity(intent);

            }
        });
        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContactActivity.this,name2.class);
                startActivity(intent);
            }
        });
        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContactActivity.this,name3.class);
                startActivity(intent);
            }
        });
    }


    public void back(View view) {
        Intent intent=new Intent(ContactActivity.this, Nav.class);
        startActivity(intent);
    }
}