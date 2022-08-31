package com.example.registration.ui.out;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OutViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;

    public OutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is out fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}