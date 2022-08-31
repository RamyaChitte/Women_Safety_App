package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NameActivity extends AppCompatActivity {
    EditText name01,no01;
    Button get01,save01;
    String ph1;
    private DatabaseReference u1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, PackageManager.PERMISSION_GRANTED);
        name01=(EditText) findViewById(R.id.name01);
        no01=(EditText) findViewById(R.id.no01);
        get01=(Button) findViewById(R.id.get01);
        get01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,
                            ContactsContract.CommonDataKinds.Phone.TYPE},
                            "DISPLAY_NAME= '"+name01.getText().toString()+"'",null,null);
                    cursor.moveToFirst();
                    no01.setText(cursor.getString(0));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        save01=(Button) findViewById(R.id.save01);
        save01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ph1=no01.getText().toString();
                Toast.makeText(NameActivity.this, "Contact Saved", Toast.LENGTH_SHORT).show();
                u1 = FirebaseDatabase.getInstance("https://fir-4ade4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                u1.child("phone1").setValue(ph1);
                Intent intent=new Intent(NameActivity.this,ContactActivity.class);
                startActivity(intent);
            }
        });
    }
}