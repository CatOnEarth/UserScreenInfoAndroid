package com.snail.userscreen.ui.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public static final String APP_PREFERENCES = "user_info";

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
            editTextAccUserName.setText(savedInstanceState.getString("textusername", ""));
            editTextAccEmailUser.setText(savedInstanceState.getString("textemailuser", ""));

        }

        mSettings = this.requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        Button bSaveInfo = binding.buttonChangeInfo;

        View.OnClickListener saveUserInfo = view -> saveUserInformation();

        bSaveInfo.setOnClickListener(saveUserInfo);

        return root;
    }

    private void saveUserInformation() {
        if (IsInfoCorrect()) {
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString("username",  editTextAccUserName.getText().toString());
            editor.putString("useremail", editTextAccEmailUser.getText().toString());
            editor.apply();
        }
    }

    private boolean IsInfoCorrect() {
        return editTextAccEmailUser.getText().toString().length() != 0 &&
                editTextAccUserName.getText().toString().length() != 0;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textusername",  editTextAccUserName.getText().toString());
        outState.putString("textemailuser", editTextAccEmailUser.getText().toString());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}