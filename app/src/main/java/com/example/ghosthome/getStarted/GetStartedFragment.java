package com.example.ghosthome.getStarted;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ghosthome.R;
import com.example.ghosthome.databinding.FragmentGetStartedBinding;

public class GetStartedFragment extends Fragment {

    private FragmentGetStartedBinding binding;
    private NavController navController;


    public GetStartedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = FragmentGetStartedBinding.inflate(inflater, container, false);
         return  binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize NavController
        navController = Navigation.findNavController(view);
        setBinding();
    }

    private void setBinding() {
        binding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the appropriate destination
                navController.navigate(R.id.action_getStartedFragment_to_logInFragment);
            }
        });
    }
}