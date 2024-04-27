package com.example.ghosthome.schedulelight.tabs

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ghosthome.databinding.FragmentTimerTabBinding
import com.example.ghosthome.schedulelight.viewmodel.AddScheduleViewModel
import com.example.ghosthome.shared.Utils
import com.google.android.material.button.MaterialButton


class TimerTabFragment : Fragment() {
    private lateinit var textViewTime: TextView
    private lateinit var progressBarCircle: ProgressBar
    lateinit var binding:FragmentTimerTabBinding
    lateinit var startTimer:MaterialButton
    lateinit var argument:String
     var isTimerRunning:Boolean = false
    private var timeLeftInMillis: Long = 0
    var utils: Utils = Utils()

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var sharedPreferences: SharedPreferences
    private val scheduleViewModel: AddScheduleViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTimerTabBinding.inflate(layoutInflater)
        initViews()
        observeValue()
        initListeners()
        return  binding.root
    }
    private fun observeValue() {
        scheduleViewModel.argumentObserver.observe(viewLifecycleOwner){
            argument = it
        }
    }
    private fun initViews() {
        progressBarCircle = binding.progressBarCircle
        textViewTime = binding.textViewTime
        startTimer = binding.btnStartStop
        binding.hourPicker.minValue = 0
        binding.hourPicker.maxValue = 23
        binding.minutePicker.minValue = 0
        binding.minutePicker.maxValue = 59
        binding.secondPicker.minValue = 0
        binding.secondPicker.maxValue = 59
        sharedPreferences = requireActivity().getSharedPreferences("TimerPrefs", MODE_PRIVATE)

    }
    private fun initListeners() {


        binding.btnHide.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnClose.setOnClickListener {
            Log.d("CANCEL","click")
            timerPause()
            findNavController().navigateUp()
        }

        val savedMillis = sharedPreferences.getLong("millisLeft", 0)
        if (savedMillis == 0.toLong()) {
        }
        else{
            binding.viewFlipper.showNext()
            startTimer(savedMillis)
        }

        binding.btnAddTimer.setOnClickListener {
            var msg = "Light ${argument.toInt() + 1}, Living Room ${argument.toInt() + 1} Timer running)"
            utils.showSnackBar(msg,binding.rootLayout)
            binding.viewFlipper.showNext()


        }




//        binding.hourPicker.wrapSelectorWheel = true
        binding.hourPicker.setOnValueChangedListener { _, _, newVal ->
            // Handle hour change
//            Toast.makeText(context, "Hour :" +newVal , Toast.LENGTH_SHORT).show()
        }

        // Configure Minute Picker
//        binding.minutePicker.wrapSelectorWheel = true
        binding.minutePicker.setOnValueChangedListener { _, _, newVal ->
//            Toast.makeText(context, "mint :" +newVal , Toast.LENGTH_SHORT).show()
            // Handle minute change
        }

        // Configure Second Picker
//        binding.secondPicker.wrapSelectorWheel = true
        binding.secondPicker.setOnValueChangedListener { _, _, newVal ->
//            Toast.makeText(context, "sec :" +newVal , Toast.LENGTH_SHORT).show()
            // Handle second change
        }
        binding.btnStartStop.setOnClickListener {


            if(binding.btnStartStop.text.equals("Start")){
                Log.i("Started", binding.btnStartStop.getText().toString());
                var totalSecond = (binding.hourPicker.value * 3600 + binding.minutePicker.value * 60 + binding.secondPicker.value) * 1000L
                binding.btnStartStop.setText("Pause")
                startTimer(totalSecond)
            } else if (binding.btnStartStop.getText().equals("Pause")){
                binding.btnStartStop.setText("Resume")
                timerPause()
            } else if (binding.btnStartStop.getText().equals("Resume")){
                binding.btnStartStop.setText("Pause")
                timerResume()
            }

//                if (binding.btnStartStop.text == "Start") {
//                    binding.btnStartStop.text = "Pause"
//                    if (!isTimerRunning) {
//                        if(timeLeftInMillis == 0.toLong()){
//                            Toast.makeText(context, "Intially satrted", Toast.LENGTH_SHORT).show()
//                            timeLeftInMillis = (binding.hourPicker.value * 3600 + binding.minutePicker.value * 60 + binding.secondPicker.value) * 1000L
//                            startTimer(timeLeftInMillis)
//                        }
//                        else{
//                            Toast.makeText(context, "RESTART", Toast.LENGTH_SHORT).show()
//
//                        startTimer(timeLeftInMillis)
//                        }
//                    } else {
//                        isTimerRunning = false
//                        Toast.makeText(context, "PAUSE time", Toast.LENGTH_SHORT).show()
//                        countDownTimer.start()
//                    }
//
//
//                } else {
//                    binding.btnStartStop.text = "Start"
//                    pauseTimer()
//
//                }
        }
    }


    private fun updateTimerDisplay(millisUntilFinished: Long,totalMilliseconds: Long) {
        val seconds = (millisUntilFinished / 1000) % 60
        val minutes = (millisUntilFinished / (1000 * 60)) % 60
        val hours = (millisUntilFinished / (1000 * 60 * 60)) % 24
        val countdownString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        timeLeftInMillis = millisUntilFinished
        textViewTime.text = countdownString
        binding.btnStartStop.setText("Pause")

        val progress = ((totalMilliseconds - millisUntilFinished) * 100 / totalMilliseconds).toInt()
        progressBarCircle.progress = progress

        sharedPreferences.edit().putLong("millisLeft", millisUntilFinished).apply()
    }

    private fun timerFinished() {
        isTimerRunning = false
        progressBarCircle.progress = 100
        sharedPreferences.edit().remove("millisLeft").apply()
        binding.viewFlipper.showPrevious()
    }

    fun timerPause() {
        countDownTimer.cancel()
    }

    private fun timerResume() {
        startTimer(timeLeftInMillis)
    }
    private fun startTimer(totalMilliseconds: Long) {

        countDownTimer = object : CountDownTimer(totalMilliseconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {


                updateTimerDisplay(millisUntilFinished,totalMilliseconds)
            }

            override fun onFinish() {
                timerFinished()
            }
        }.start()

        isTimerRunning = true

//        countDownTimer = object : CountDownTimer(totalMilliseconds, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                val seconds = (millisUntilFinished / 1000) % 60
//                val minutes = (millisUntilFinished / (1000 * 60)) % 60
//                val hours = (millisUntilFinished / (1000 * 60 * 60)) % 24
//
//                val countdownString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
//                textViewTime.text = countdownString
//                val progress = ((totalMilliseconds - millisUntilFinished) * 100 / totalMilliseconds).toInt()
//                sharedPreferences.edit().putLong("millisLeft", millisUntilFinished).apply()
////                val intent = Intent(context, TimerService::class.java)
////                requireActivity().startService(intent)
//
//                progressBarCircle.progress = progress
//            }
//
//            override fun onFinish() {
//                isTimerRunning = false
//                progressBarCircle.progress = 100
//                sharedPreferences.edit().remove("millisLeft").apply()
////                requireActivity().stopService(intent)
//                            binding.viewFlipper.showPrevious()
//
//            }
//        }.start()
//        isTimerRunning = true
    }

    override fun onDestroy() {
        super.onDestroy()
//        val intent = Intent(context, TimerService::class.java)
//        requireActivity().startService(intent)
    }

}