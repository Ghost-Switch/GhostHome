package com.example.ghosthome.schedulelight

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.ghosthome.R
import com.example.ghosthome.databinding.FragmentScheduleLightDialogBinding
import com.example.ghosthome.ghostHome.OuterTabAdapter
import com.example.ghosthome.ghostHome.livingRoom.KitchenFragment
import com.example.ghosthome.ghostHome.livingRoom.LivingRoomFragment
import com.example.ghosthome.schedulelight.tabs.ScheduleTabFragment
import com.example.ghosthome.schedulelight.tabs.TimerTabFragment
import com.example.ghosthome.schedulelight.viewmodel.AddScheduleViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ScheduleLightDialogFragment : DialogFragment() {
    lateinit var binding: FragmentScheduleLightDialogBinding
    lateinit var viewPager:ViewPager2
    val addScheduleViewModel:AddScheduleViewModel by activityViewModels()
    lateinit var tabLayout:TabLayout



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleLightDialogBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val integerArgument = arguments?.getInt("arg") ?: 0
        if (integerArgument != null){
            binding.tvTitle.text = "Light ${integerArgument + 1} Schedule and Timer"
            addScheduleViewModel.setArgument(integerArgument.toString())
        }
        setOnClickListner()
        setData()
        return binding.root
    }

    private fun setOnClickListner() {
        binding.btnAddSchedule.setOnClickListener {
            tabLayout.getTabAt(0)?.select()
            addScheduleViewModel.clickedAdd(true)
        }
    }


    private fun setData() {
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager
        val adapter = OuterTabAdapter(parentFragmentManager, lifecycle)
        adapter.addFragment(ScheduleTabFragment(), getString(R.string.schedule))
        adapter.addFragment(TimerTabFragment(), getString(R.string.timer))

        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }


}


