package com.snail.userscreen.ui.account;

import static com.snail.userscreen.MainActivity.APP_PREFERENCES;
import static com.snail.userscreen.MainActivity.APP_PREFERENCES_USERNAME;
import static com.snail.userscreen.MainActivity.APP_PREFERENCES_USER_EMAIL;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.snail.userscreen.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    private EditText editTextAccUserName;
    private EditText editTextAccEmailUser;

    private final String KEY_SAVED_INSTANCE_USERNAME   = "ed_text_username";
    private final String KEY_SAVED_INSTANCE_USER_EMAIL = "ed_text_user_email";

    private SharedPreferences mSettings;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textViewAccUserName  = binding.textViewAccUserName;
        final TextView textViewAccEmailUser = binding.textViewAccUserEmail;

        accountViewModel.getTextUsername().observe(getViewLifecycleOwner(), textViewAccUserName::setText);
        accountViewModel.getTextEmailUser().observe(getViewLifecycleOwner(), textViewAccEmailUser::setText);

        editTextAccUserName  = binding.editTextTextPersonName;
        editTextAccEmailUser = binding.editTextTextEmailAddress;

        if (savedInstanceState == null) {
            editTextAccUserName.setText("");
            editTextAccEmailUser.setText("");
        } else {
            editTextAccUserName.setText(savedInstanceState.getString( KEY_SAVED_INSTANCE_USERNAME,   ""));
            editTextAccEmailUser.setText(savedInstanceState.getString(KEY_SAVED_INSTANCE_USER_EMAIL, ""));

        }

        mSettings = this.requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        Button bSaveInfo = binding.buttonChangeInfo;

        View.OnClickListener saveUserInfo = view -> saveUserInformation();

        bSaveInfo.setOnClickListener(saveUserInfo);

        return root;
    }

    private void closeKeyboard() {
        View view = requireActivity().getCurrentFocus();

        if (view != null) {
            InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void saveUserInformation() {
        if (IsInfoCorrect()) {
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_USERNAME,   editTextAccUserName.getText().toString());
            editor.putString(APP_PREFERENCES_USER_EMAIL, editTextAccEmailUser.getText().toString());
            editor.apply();

            clearInfo();
            closeKeyboard();
        }
    }

    private void clearInfo() {
        editTextAccUserName.setText( "");
        editTextAccEmailUser.setText("");
    }

    private boolean IsInfoCorrect() {
        return  editTextAccEmailUser.getText().toString().length() != 0 &&
                editTextAccUserName.getText().toString().length()  != 0;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (editTextAccUserName != null && editTextAccEmailUser != null) {
            outState.putString(KEY_SAVED_INSTANCE_USERNAME,   editTextAccUserName.getText().toString());
            outState.putString(KEY_SAVED_INSTANCE_USER_EMAIL, editTextAccEmailUser.getText().toString());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}