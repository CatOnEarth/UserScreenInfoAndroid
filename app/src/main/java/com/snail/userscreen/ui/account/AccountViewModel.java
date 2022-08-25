package com.snail.userscreen.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class AccountViewModel extends ViewModel {

    private final MutableLiveData<String> textUsername;
    private final MutableLiveData<String> textEmailUser;

    public AccountViewModel() {
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

    public void SetUsername(String username) {
        textUsername.setValue(username);
    }

    public void SetEmailUser(String email) {
        textEmailUser.setValue(email);
    }
}
