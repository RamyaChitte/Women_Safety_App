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

public class name2 extends AppCompatActivity {
    EditText name03,no03;
    Button get03,save03;
    private DatabaseReference u3;
    String ph3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name2);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, PackageManager.PERMISSION_GRANTED);
        name03=(EditText) findViewById(R.id.name03);
        no03=(EditText) findViewById(R.id.no03);
        get03=(Button) findViewById(R.id.get03);
        get03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,
                                    ContactsContract.CommonDataKinds.Phone.TYPE},
                            "DISPLAY_NAME= '"+name03.getText().toString()+"'",null,null);
                    cursor.moveToFirst();
                    no03.setText(cursor.getString(0));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        save03=(Button) findViewById(R.id.save03);
        save03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ph3=no03.getText().toString();
                Toast.makeText(name2.this, "Contact Saved", Toast.LENGTH_SHORT).show();
                u3 = FirebaseDatabase.getInstance("https://fir-4ade4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                u3.child("phone3").setValue(ph3);
                Intent intent=new Intent(name2.this,ContactActivity.class);
                startActivity(intent);
            }
        });
    }
}
