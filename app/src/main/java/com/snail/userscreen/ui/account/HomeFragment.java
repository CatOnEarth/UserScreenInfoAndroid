package com.snail.userscreen.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.snail.userscreen.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private EditText editTextAccUserName;
    private EditText editTextAccEmailUser;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel accountViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textViewAccUserName  = binding.textViewAccUserName;
        final TextView textViewAccEmailUser = binding.textViewAccUserEmail;

        accountViewModel.getText().observe(getViewLifecycleOwner(), textViewAccUserName::setText);
        accountViewModel.getkText().observe(getViewLifecycleOwner(), textViewAccEmailUser::setText);

        editTextAccUserName  = binding.editTextTextPersonName;
        editTextAccEmailUser = binding.editTextTextEmailAddress;

        if (savedInstanceState == null) {
            editTextAccUserName.setText("");
            editTextAccEmailUser.setText("");
        } else {
            editTextAccUserName.setText(savedInstanceState.getString("textusername", ""));
            editTextAccEmailUser.setText(savedInstanceState.getString("textemailuser", ""));

        }

        return root;
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