package com.example.ghosthome.nameOfHome;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ghosthome.GhostHomeActivity;
import com.example.ghosthome.R;
import com.example.ghosthome.databinding.FragmentHomeNameBinding;


public class HomeNameFragment extends Fragment {

    private FragmentHomeNameBinding binding;



    public HomeNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentHomeNameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setBinding();
    }

    private void setBinding() {

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the appropriate destination
// Create an Intent to navigate from LogInFragment to HomeNameFragment
                Intent intent = new Intent(getActivity(), GhostHomeActivity.class);
                startActivity(intent);            }
        });
    }
}