package com.example.ghosthome

import android.animation.ValueAnimator
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.addroom.OnClickItem
import com.example.ghosthome.databinding.ActivityMainBinding
import com.example.ghosthome.home.adapter.SidebarAdapter
import com.example.ghosthome.home.adapter.model.SidebarModel
import com.example.ghosthome.service.TimerService
import java.sql.Time


class MainActivity : AppCompatActivity(), OnClickItem {
    lateinit var binding: ActivityMainBinding
     lateinit var btn : ImageButton
     lateinit var layout : LinearLayout
    private var isExpanded = false
    lateinit var rv:RecyclerView
    lateinit var adapter: SidebarAdapter
     private var list: List<SidebarModel> = ArrayList<SidebarModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        setSideBar()
        btn = findViewById(R.id.btnClose)
        layout = findViewById(R.id.sidebarContainer)
        btn.setOnClickListener {
//
            if (isExpanded) {
                collapseSidebar()
            } else {
                expandSidebar()
            }
        }


    }



    private fun setSideBar() {


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        // Listen for changes in the current destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Check the ID of the current destination
            when (destination.id) {
                R.id.logInFragment -> {
                    // Hide the side menu on the authentication screen
                    binding.sidebarContainer.visibility = View.GONE
                }
                R.id.getStartedFragment -> {
                    // Hide the side menu on the authentication screen
                    binding.sidebarContainer.visibility = View.GONE
                }
                R.id.homeNameFragment -> {
                    // Hide the side menu on the authentication screen
                    binding.sidebarContainer.visibility = View.GONE
                }

                else -> {
                    // Show the side menu on other screens
                    binding.sidebarContainer.visibility = View.VISIBLE
                }
            }
        }





    }

    private fun initData() {
        list = listOf(
            SidebarModel("Home",R.drawable.ic_home,null,null),
            SidebarModel("Rooms",R.drawable.ic_rooms,null,null),
            SidebarModel("Devices",R.drawable.ic_devices,null,null),
            SidebarModel("Pinned Devices",R.drawable.ic_pin,null,null),
            SidebarModel("Settings",R.drawable.ic_settings,null,null),
        )
        adapter = SidebarAdapter(list, this,this)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

    }

    private fun expandSidebar() {
        val sidebarContainer = layout
        val layoutParams = sidebarContainer.layoutParams
        val density = resources.displayMetrics.density
        val pixels = (70 * density).toInt()
        val anim = ValueAnimator.ofInt(layoutParams.width, pixels)
        anim.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            layoutParams.width = value
            sidebarContainer.layoutParams = layoutParams
        }
        isExpanded=true
        anim.duration = 500
        binding.btnClose.setImageResource(R.drawable.round_arrow_forward_ios_24)
        anim.start()
        binding.title.visibility = View.GONE
        showLayoutVisible(View.GONE)
        adapter.setTextVisibility(false)

    }

    private fun showLayoutVisible(gone: Int) {
        binding.layoutAir.visibility = gone
        binding.layoutSwitch.visibility = gone

    }


    private fun collapseSidebar() {
        val sidebarContainer = layout
        val layoutParams = sidebarContainer.layoutParams
        val density = resources.displayMetrics.density
        val pixels = (200 * density).toInt()

        val anim = ValueAnimator.ofInt(layoutParams.width, pixels)
        anim.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            layoutParams.width = value
            sidebarContainer.layoutParams = layoutParams
        }
        isExpanded=false
        anim.duration = 300
        binding.btnClose.setImageResource(R.drawable.ic_close)
        anim.start()
        Handler().postDelayed({
            binding.title.visibility = View.VISIBLE
            adapter.setTextVisibility(true)
            showLayoutVisible(View.VISIBLE)
        }, 300)

    }

    override fun onclick(pos: Int) {
        when(pos){
            0 -> {
                        binding.fragmentContainerView.findNavController().navigate(R.id.ghostHomeActivity)

            }
            1 -> {
                binding.fragmentContainerView.findNavController().navigate(R.id.addRoomFragment)

            }
            3 -> {
                binding.fragmentContainerView.findNavController().navigate(R.id.pinDeviceFragment)

            }

        }
    }

}