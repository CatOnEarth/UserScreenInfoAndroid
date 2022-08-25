package com.snail.userscreen.ui.information;

import static com.snail.userscreen.ui.account.AccountFragment.APP_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InformationViewModel extends ViewModel {

    private final MutableLiveData<String> textUsername;
    private final MutableLiveData<String> textEmailUser;

    public InformationViewModel() {
        textUsername = new MutableLiveData<>();
        textUsername.setValue("Введите новое имя");

        textEmailUser = new MutableLiveData<>();
        textEmailUser.setValue("Введите новый email");
    }

    public LiveData<String> getTextUsername() {
        return textUsername;
    }

    public LiveData<String> getTextEmailUser() {
        return textEmailUser;
    }

    public void setData(InformationFragment context) {
        SharedPreferences mSettings;
        mSettings = context.requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        textUsername.setValue(mSettings.getString("username", ""));
        textEmailUser.setValue(mSettings.getString("useremail", ""));
    }
}