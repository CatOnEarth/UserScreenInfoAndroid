package com.snail.userscreen.ui.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.snail.userscreen.databinding.FragmentInformationBinding;

/** Information fragment */
public class InformationFragment extends Fragment {

    /** Allows you to bind UI components to instances to data sources in your application using a declarative format, non-programmatically. */
    private FragmentInformationBinding binding;

    /**Called to have the fragment instantiate its user interface view
     *
     * @param inflater LayoutInflater: The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container ViewGroup: If non-null, this is the parent view that the fragment's UI should be attached to. The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState Bundle: If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InformationViewModel informationViewModel =
                new ViewModelProvider(this).get(InformationViewModel.class);

        informationViewModel.setData(this);

        binding = FragmentInformationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textViewUsername  = binding.textViewDisplayUsername;
        final TextView textViewEmailUser = binding.textViewDisplayEmailUser;

        informationViewModel.getTextUsername().observe(getViewLifecycleOwner(), textViewUsername::setText);
        informationViewModel.getTextEmailUser().observe(getViewLifecycleOwner(), textViewEmailUser::setText);

        return root;
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