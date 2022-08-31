package com.example.registration.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.registration.R;
import com.example.registration.databinding.FragmentFeedsBinding;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class feeds extends Fragment {
    TextView rateCount,showRating;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue;
    String stars,rev;
    private DatabaseReference myRef,myRef2;


    private FragmentFeedsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FeedsViewModel feedsViewModel =
                new ViewModelProvider(this).get(FeedsViewModel.class);

        binding = FragmentFeedsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();;
        rateCount=root.findViewById(R.id.rateCount);
        ratingBar=root.findViewById(R.id.ratingBar);
        review=root.findViewById(R.id.review);
        submit=root.findViewById(R.id.submit);
        //FirebaseDatabase database = FirebaseDatabase.getInstance("https://console.firebase.google.com/project/fir-4ade4/database/fir-4ade4-default-rtdb/data/~2F");
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue=ratingBar.getRating();
                if(rateValue<=1 && rateValue>0)
                    rateCount.setText("Bad "+rateValue+"/5");
                else if(rateValue<=2 && rateValue>1)
                    rateCount.setText("OK "+rateValue+"/5");
                else if(rateValue<=3 && rateValue>2)
                    rateCount.setText("Good "+rateValue+"/5");
                else if(rateValue<=4 && rateValue>3)
                    rateCount.setText("Very Good "+rateValue+"/5");
                else if(rateValue<=5 && rateValue>4)
                    rateCount.setText("Best "+rateValue+"/5");
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stars = rateCount.getText().toString();
                rev = review.getText().toString();
                Toast.makeText(getActivity(), "Thanks for your review!", Toast.LENGTH_SHORT).show();
                myRef =FirebaseDatabase.getInstance("https://fir-4ade4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                myRef.child("Stars").setValue(stars);
                myRef2 =FirebaseDatabase.getInstance("https://fir-4ade4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                myRef2.child("Reviews").setValue(rev);
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
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