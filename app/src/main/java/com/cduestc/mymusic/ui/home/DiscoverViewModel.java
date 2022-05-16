package com.cduestc.mymusic.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiscoverViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DiscoverViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("发现");
    }

    public LiveData<String> getText() {
        return mText;
    }
}