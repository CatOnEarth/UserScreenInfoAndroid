package com.snail.userscreen.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/** ViewModel of account fragment */
public class AccountViewModel extends ViewModel {

    /** Text username */
    private final MutableLiveData<String> textUsername;
    /** Text user's email */
    private final MutableLiveData<String> textEmailUser;

    /**Constructor
     *
     */
    public AccountViewModel() {
        textUsername = new MutableLiveData<>();
        textUsername.setValue("Введите новое имя");

        textEmailUser = new MutableLiveData<>();
        textEmailUser.setValue("Введите новый email");
    }

    /**Getter username
     *
     * @return LiveData<String> username
     */
    public LiveData<String> getTextUsername() {
        return textUsername;
    }

    /**Getter user's email
     *
     * @return LiveData<String> user's email
     */
    public LiveData<String> getTextEmailUser() {
        return textEmailUser;
    }
}
