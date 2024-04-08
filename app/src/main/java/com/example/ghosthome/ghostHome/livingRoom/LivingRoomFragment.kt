package com.example.ghosthome.ghostHome.livingRoom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.ghosthome.R
import com.example.ghosthome.databinding.FragmentHomeTabBinding
import com.example.ghosthome.ghostHome.OuterTabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class LivingRoomFragment(
) : Fragment() {

    private lateinit var binding: FragmentHomeTabBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeTabBinding.inflate(inflater, container, false)
        setBinding()
        return binding.root
    }

    private fun setBinding() {

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        val adapter = OuterTabAdapter(childFragmentManager, lifecycle)
        adapter.addFragment(AllActiveFragment(), getString(R.string.all_active))
        adapter.addFragment(LightsAndSocketFragment(), getString(R.string.light_sockets))
        adapter.addFragment(SmartLocksFragment(), getString(R.string.smart_locks))

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }


}