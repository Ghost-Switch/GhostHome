package com.example.ghosthome.ghostHome.livingRoom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ghosthome.R
import com.example.ghosthome.databinding.FragmentLightsAndSocketBinding


class LightsAndSocketFragment : Fragment() {

    private lateinit var binding: FragmentLightsAndSocketBinding
    private lateinit var lightsAndSocketAdapter: LightsAndSocketAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentLightsAndSocketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lightsAndSockets = mutableListOf(
            LightsAndSockets("light 1", false),
            LightsAndSockets("light 2", false),

        )

        lightsAndSocketAdapter = LightsAndSocketAdapter(lightsAndSockets)
        binding.recyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerview.adapter = lightsAndSocketAdapter

    }


}