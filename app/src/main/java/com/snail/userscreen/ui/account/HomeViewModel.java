package com.snail.userscreen.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.snail.userscreen.R;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> kText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Введите новое имя");

        kText = new MutableLiveData<>();
        kText.setValue("Введите новый email");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getkText() {
        return kText;
    }
}