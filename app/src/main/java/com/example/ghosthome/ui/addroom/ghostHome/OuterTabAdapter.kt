package com.example.ghosthome.ui.addroom.ghostHome

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OuterTabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    // List of fragments to be displayed by the ViewPager2
    private val fragmentList = mutableListOf<Fragment>()
    private val titleList = mutableListOf<String>()

    override fun getItemCount(): Int {
        return  fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment, title : String){
        fragmentList.add(fragment)
        titleList.add(title)
    }

    fun getTitle(position: Int): String {
        return titleList[position]
    }
}