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

/** Account fragment */
public class AccountFragment extends Fragment {

    /** Allows you to bind UI components to instances to data sources in your application using a declarative format, non-programmatically. */
    private FragmentAccountBinding binding;

    /** EditText username */
    private EditText editTextAccUserName;
    /** EditText user's email */
    private EditText editTextAccEmailUser;

    /** Key of username to saved instance  */
    private final String KEY_SAVED_INSTANCE_USERNAME   = "ed_text_username";
    /** Key of user's email to saved instance */
    private final String KEY_SAVED_INSTANCE_USER_EMAIL = "ed_text_user_email";

    /**Called to have the fragment instantiate its user interface view
     *
     * @param inflater LayoutInflater: The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container ViewGroup: If non-null, this is the parent view that the fragment's UI should be attached to. The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState Bundle: If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
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



        Button bSaveInfo = binding.buttonChangeInfo;

        View.OnClickListener saveUserInfo = view -> saveUserInformation();

        bSaveInfo.setOnClickListener(saveUserInfo);

        return root;
    }

    /**Hide keyboard
     *
     */
    private void closeKeyboard() {
        View view = requireActivity().getCurrentFocus();

        if (view != null) {
            InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**Save information about user
     *
     */
    private void saveUserInformation() {
        if (IsInfoCorrect()) {
            SharedPreferences mSettings = this.requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_USERNAME,   editTextAccUserName.getText().toString());
            editor.putString(APP_PREFERENCES_USER_EMAIL, editTextAccEmailUser.getText().toString());
            editor.apply();

            clearInfo();
            closeKeyboard();
        }
    }

    /**Clear edittext username and email
     *
     */
    private void clearInfo() {
        editTextAccUserName.setText( "");
        editTextAccEmailUser.setText("");
    }

    /**Check correction input username and email
     *
     * @return true if correct input
     * @return false if incorrect input
     */
    private boolean IsInfoCorrect() {
        return  editTextAccEmailUser.getText().toString().length() != 0 &&
                editTextAccUserName.getText().toString().length()  != 0;
    }

    /**Called to retrieve per-instance state from an activity before being killed so that the state can be restored
     *
     * @param outState associative array, i.e. storing key-value pairs
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (editTextAccUserName != null && editTextAccEmailUser != null) {
            outState.putString(KEY_SAVED_INSTANCE_USERNAME,   editTextAccUserName.getText().toString());
            outState.putString(KEY_SAVED_INSTANCE_USER_EMAIL, editTextAccEmailUser.getText().toString());
        }
    }

    /**Called when the view previously created by onCreateView(LayoutInflater, ViewGroup, Bundle) has been detached from the fragment
     *
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}