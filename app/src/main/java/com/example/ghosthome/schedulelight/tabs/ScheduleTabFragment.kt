package com.example.ghosthome.schedulelight.tabs

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.TimePicker.OnTimeChangedListener
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.R
import com.example.ghosthome.databinding.FragmentScheduleTabBinding
import com.example.ghosthome.schedulelight.adapter.ScheduleRecyclerViewAdapter
import com.example.ghosthome.schedulelight.model.ScheduleTimeModel
import com.example.ghosthome.schedulelight.swipehelper.SwipeToDeleteCallback
import com.example.ghosthome.schedulelight.viewmodel.AddScheduleViewModel
import com.example.ghosthome.shared.Utils
import com.google.android.material.snackbar.Snackbar


class ScheduleTabFragment : Fragment() {
    lateinit var binding: FragmentScheduleTabBinding
     private var startTime: String = ""
     private var endTime:String = ""
      var utils: Utils = Utils()
    private lateinit var scheduleTimeModel: ScheduleTimeModel
     var type:String = "Never Repeat"
    private var positionValue = -1
    lateinit var argument:String
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: ScheduleRecyclerViewAdapter
    var scheduleList = ArrayList<ScheduleTimeModel>()
    var coordinatorLayout: CoordinatorLayout? = null

    private val scheduleViewModel:AddScheduleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleTabBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerView
        coordinatorLayout = binding.coordinatorLayout
        setSpinner()
        setOnClickListner()
        onObserve()
        populateRecyclerView()
        enableSwipeToDeleteAndUndo()

        return binding.root
    }

    private fun populateRecyclerView() {

        mAdapter =  ScheduleRecyclerViewAdapter(scheduleList,requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mAdapter

    }

    private fun onObserve() {
        scheduleViewModel.dataList.observe(viewLifecycleOwner){
            if(it ==  null){
                Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show()
                binding.hintText.visibility = View.VISIBLE
            }
            else{
                scheduleList.add(it)
                recyclerView.adapter!!.notifyDataSetChanged()
                binding.hintText.visibility = View.GONE
            }

        }
        scheduleViewModel.argumentObserver.observe(viewLifecycleOwner){
            argument = it
        }


        scheduleViewModel.isClickAdd.observe(viewLifecycleOwner){
            if (it){
                binding.viewFlipper.displayedChild = 1
            }
            else{
//                binding.viewFlipper.showPrevious()
                binding.viewFlipper.displayedChild = 2
            }
        }
    }

    private fun setOnClickListner() {


        binding.startTimePicker.setIs24HourView(false)
        binding.startTimePicker.setOnTimeChangedListener(OnTimeChangedListener { _, hour, minute ->
            var hour = hour
            var am_pm = ""
            // AM_PM decider logic
            when {hour == 0 -> { hour += 12
                am_pm = "AM"
            }
                hour == 12 -> am_pm = "PM"
                hour > 12 -> { hour -= 12
                    am_pm = "PM"
                }
                else -> am_pm = "AM"
            }
            if (am_pm != null) {
                val hour = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute
                // display format of time
                val msg = "$hour:$min $am_pm"
                 startTime = msg

            }

        })
        binding.endTimePicker.setOnTimeChangedListener(OnTimeChangedListener { _, hour, minute ->
            var hour = hour
            var am_pm = ""
            // AM_PM decider logic
            when {hour == 0 -> { hour += 12
                am_pm = "AM"
            }
                hour == 12 -> am_pm = "PM"
                hour > 12 -> { hour -= 12
                    am_pm = "PM"
                }
                else -> am_pm = "AM"
            }
            if (am_pm != null) {
                val hour = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute
                // display format of time
                val msg = "$hour:$min $am_pm"
                 endTime = msg
            }

        })

        binding.btnSave.setOnClickListener {
            if(startTime.isEmpty()){
                Toast.makeText(context, "Select Start time", Toast.LENGTH_SHORT).show()
            }
            else if(endTime.isEmpty()){
                Toast.makeText(context, "Select end time", Toast.LENGTH_SHORT).show()
            }
            else {
                scheduleTimeModel = ScheduleTimeModel(startTime,endTime,type);
                scheduleViewModel.addData(scheduleTimeModel)
                scheduleViewModel.clickedAdd(false)
                var msg = "Light ${argument + 1}, Living Room Scheduled for ${startTime} to ${endTime})"
                utils.showSnackBar(msg,binding.rootLayout)


//                binding.viewFlipper.showPrevious()
            }
        }

//        binding.btnAdd.setOnClickListener {
//            binding.viewFlipper.showNext()
//        }
    }



    private fun setSpinner() {
        val languages = resources.getStringArray(R.array.scheduleList)

        val spinner = binding.spinner
        if (spinner != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, languages )
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                type = selectedItem
                

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback: SwipeToDeleteCallback = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val item: ScheduleTimeModel = mAdapter.data.get(position)
//                scheduleList.removeAt(position)
                mAdapter.removeItem(position)
                val snackbar = Snackbar
                    .make(
                        coordinatorLayout!!,
                        "Item was removed from the list.",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction("UNDO") {
                     positionValue = position
//                    scheduleViewModel.updateData(item)

                    mAdapter.restoreItem(item, position)
                    recyclerView!!.scrollToPosition(position)
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(recyclerView)
    }


}