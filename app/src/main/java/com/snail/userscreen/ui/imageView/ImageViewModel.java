package com.snail.userscreen.ui.imageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/** ViewModel of Information fragment */
public class ImageViewModel extends ViewModel {

    /** Funny text */
    private final MutableLiveData<String> mText;

    /**Constructor
     *
     */
    public ImageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Просто красивый котик)0)");
    }

    /**Getter funny text
     *
     * @return LiveData<String> funny text
     */
    public LiveData<String> getText() {
        return mText;
    }

}