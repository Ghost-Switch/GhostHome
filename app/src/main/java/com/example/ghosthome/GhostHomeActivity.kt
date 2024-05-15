package com.example.ghosthome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Adapter
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.ghosthome.databinding.ActivityGhostHomeBinding
import com.example.ghosthome.databinding.FragmentAddRoomBinding
import com.example.ghosthome.ui.addroom.ghostHome.OuterTabAdapter
import com.example.ghosthome.ghostHome.livingRoom.KitchenFragment
import com.example.ghosthome.ui.addroom.ghostHome.livingRoom.LivingRoomFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GhostHomeActivity : Fragment() {

    private lateinit var binding: ActivityGhostHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityGhostHomeBinding.inflate(layoutInflater)
        setBinding()
        return  binding.root
    }

    private fun setBinding() {
        val homeTabLayout = binding.homeTab
        tabLayout = homeTabLayout.backgroundTabLayout
        viewPager = binding.viewPager
        val adapter = OuterTabAdapter(parentFragmentManager, lifecycle)
        adapter.addFragment(LivingRoomFragment(), getString(R.string.living_room))
        adapter.addFragment(KitchenFragment(), getString(R.string.kitchen))

        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }

}