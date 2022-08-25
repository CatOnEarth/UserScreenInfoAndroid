package com.snail.userscreen.ui.information;

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
}