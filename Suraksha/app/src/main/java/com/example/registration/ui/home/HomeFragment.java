package com.example.registration.ui.home;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.registration.CallActivity;
import com.example.registration.ContactActivity;
import com.example.registration.MapsActivity;
import com.example.registration.R;
import com.example.registration.YoutubeActivity;
import com.example.registration.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    ImageView callemg,defend,alarm,addc;
    MediaPlayer mp;
    boolean flag=false;
    private FragmentHomeBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        callemg=(ImageView) root.findViewById(R.id.callemg);
        callemg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CallActivity.class);
                startActivity(intent);
            }
        });
        defend=(ImageView) root.findViewById(R.id.defend);
        defend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),YoutubeActivity.class);
                startActivity(intent);
            }
        });
        alarm=(ImageView) root.findViewById(R.id.alarm);
        mp=MediaPlayer.create(getActivity(),R.raw.police);
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==true) {
                    mp.release();
                    flag=false;
                    mp=MediaPlayer.create(getActivity(),R.raw.police);
                }
                else{
                    mp.start();
                    flag=true;
                }
                Intent intent=new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });
        addc=(ImageView) root.findViewById(R.id.get01);
        addc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}