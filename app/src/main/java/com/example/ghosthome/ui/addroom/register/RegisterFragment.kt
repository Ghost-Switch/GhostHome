package com.example.ghosthome.ui.addroom.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ghosthome.R
import com.example.ghosthome.databinding.FragmentGetStartedBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentGetStartedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetStartedBinding.inflate(inflater, container, false)
        setBinding()
        return binding.root
    }

    private fun setBinding() {
        binding.isAllFieldValid = true
        binding.btnCreateAccount.setOnClickListener {
            // Navigate to the appropriate destination
            findNavController().navigate(R.id.action_getStartedFragment_to_logInFragment);
        }
    }
}