package com.cduestc.mymusic.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("我的");
    }

    public LiveData<String> getText() {
        return mText;
    }
}