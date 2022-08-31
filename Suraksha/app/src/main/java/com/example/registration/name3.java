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

public class name3 extends AppCompatActivity {
    EditText name04,no04;
    Button get04,save04;
    private DatabaseReference u4;
    String ph4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name3);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, PackageManager.PERMISSION_GRANTED);
        name04=(EditText) findViewById(R.id.name04);
        no04=(EditText) findViewById(R.id.no04);
        get04=(Button) findViewById(R.id.get04);
        get04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,
                                    ContactsContract.CommonDataKinds.Phone.TYPE},
                            "DISPLAY_NAME= '"+name04.getText().toString()+"'",null,null);
                    cursor.moveToFirst();
                    no04.setText(cursor.getString(0));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        save04=(Button) findViewById(R.id.save04);
        save04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ph4=no04.getText().toString();
                Toast.makeText(name3.this, "Contact Saved", Toast.LENGTH_SHORT).show();
                u4 = FirebaseDatabase.getInstance("https://fir-4ade4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                u4.child("phone4").setValue(ph4);
                Intent intent=new Intent(name3.this,ContactActivity.class);
                startActivity(intent);
            }
        });
    }
}
