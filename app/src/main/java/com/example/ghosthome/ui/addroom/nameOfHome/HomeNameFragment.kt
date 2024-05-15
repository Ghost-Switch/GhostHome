package com.example.ghosthome.ui.addroom.nameOfHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ghosthome.R
import com.example.ghosthome.databinding.FragmentHomeNameBinding

class HomeNameFragment : Fragment() {

    private lateinit var binding : FragmentHomeNameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeNameBinding.inflate(inflater, container, false)
        setBinding()
        return binding.root
    }

    private fun setBinding() {
        binding.isAllFieldValid = true
        binding.btnContinue.setOnClickListener {
            // Navigate to the appropriate destination
            findNavController().navigate(R.id.action_homeNameFragment_to_ghostHomeActivity);
        }
    }
}