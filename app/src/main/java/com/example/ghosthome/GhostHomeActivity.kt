package com.example.ghosthome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Adapter
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.ghosthome.databinding.ActivityGhostHomeBinding
import com.example.ghosthome.ghostHome.OuterTabAdapter
import com.example.ghosthome.ghostHome.livingRoom.KitchenFragment
import com.example.ghosthome.ghostHome.livingRoom.LivingRoomFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GhostHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGhostHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGhostHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBinding()


    }

    private fun setBinding() {
        val homeTabLayout = binding.homeTab

        tabLayout = homeTabLayout.tabLayout
        viewPager = homeTabLayout.viewPager

        val adapter = OuterTabAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(LivingRoomFragment(), getString(R.string.living_room))
        adapter.addFragment(KitchenFragment(), getString(R.string.kitchen))



        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }

}