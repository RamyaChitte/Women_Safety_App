package com.example.registration;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.registration.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private final long MIN_TIME=10000;
    private final long MIN_DIST=5;
    private LatLng latLng;
    private ActivityMapsBinding binding;
    private DatabaseReference ref1,ref2,ref3,ref4;
    String ph1,ph2,ph3,ph4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                try{
                    latLng=new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("My Position"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    ref1= FirebaseDatabase.getInstance("https://fir-4ade4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("phone1");
                    ref2= FirebaseDatabase.getInstance("https://fir-4ade4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("phone2");
                    ref3= FirebaseDatabase.getInstance("https://fir-4ade4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("phone3");
                    ref4= FirebaseDatabase.getInstance("https://fir-4ade4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("phone4");
                    ref1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ph1=snapshot.getValue().toString();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                    ref2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ph2=snapshot.getValue().toString();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                    ref3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ph3=snapshot.getValue().toString();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                    ref4.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ph4=snapshot.getValue().toString();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                    String myLatitude=String.valueOf(location.getLatitude());
                    String myLongitude=String.valueOf(location.getLongitude());
                    String msg="Help Me!! I am at \nLatitude: "+myLatitude+"\nLongitude: "+myLongitude;
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(ph1,null,msg,null,null);
                    smsManager.sendTextMessage(ph2,null,msg,null,null);
                    smsManager.sendTextMessage(ph3,null,msg,null,null);
                    smsManager.sendTextMessage(ph4,null,msg,null,null);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);
        try{
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME,MIN_DIST,locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DIST,locationListener);
        }
        catch(SecurityException e){
            e.printStackTrace();
        }

    }
}