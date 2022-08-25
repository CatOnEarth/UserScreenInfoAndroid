package com.snail.userscreen.ui.imageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImageViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ImageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Просто красивый котик)0)");
    }

    public LiveData<String> getText() {
        return mText;
    }

}