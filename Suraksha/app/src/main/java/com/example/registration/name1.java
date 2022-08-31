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

public class name1 extends AppCompatActivity {
 EditText name02,no02;
 Button get02,save02;
 private DatabaseReference u2;
 String ph2;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name1);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, PackageManager.PERMISSION_GRANTED);
        name02=(EditText) findViewById(R.id.name02);
        no02=(EditText) findViewById(R.id.no02);
        get02=(Button) findViewById(R.id.get02);
        get02.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        try{
        Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone.TYPE},
        "DISPLAY_NAME= '"+name02.getText().toString()+"'",null,null);
        cursor.moveToFirst();
        no02.setText(cursor.getString(0));
        }
        catch(Exception e){
        e.printStackTrace();
        }
        }
        });
        save02=(Button) findViewById(R.id.save02);
        save02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        ph2=no02.getText().toString();
                        Toast.makeText(name1.this, "Contact Saved", Toast.LENGTH_SHORT).show();
                        u2 = FirebaseDatabase.getInstance("https://fir-4ade4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                        u2.child("phone2").setValue(ph2);
                        Intent intent=new Intent(name1.this,ContactActivity.class);
                        startActivity(intent);
                }
        });
        }

}
