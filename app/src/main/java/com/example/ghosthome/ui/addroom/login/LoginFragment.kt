package com.example.ghosthome.ui.addroom.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ghosthome.R
import com.example.ghosthome.databinding.FragmentLogInBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        setBinding()
        return binding.root
    }

    private fun setBinding() {
        binding.isAllFieldValid = true
        binding.btnLogin.setOnClickListener {
            // Navigate to the appropriate destination
            findNavController().navigate(R.id.action_logInFragment_to_homeNameFragment);
        }
    }
}