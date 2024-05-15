package com.example.ghosthome.ui.addroom.ghostHome.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.ghosthome.R
import com.example.ghosthome.ui.addroom.adapter.CustomSpinnerAdapter
import com.example.ghosthome.ui.addroom.model.AddRoomModel
import com.example.ghosthome.ui.addroom.viewmodel.AddRoomViewModel
import com.example.ghosthome.databinding.FragmentAddLightDialogBinding
import com.example.ghosthome.ui.addroom.ghostHome.viewmodel.SocketLightViewModel
import com.example.ghosthome.ui.addroom.adapter.model.SidebarModel

class AddLightDialogFragment : DialogFragment() {
    lateinit var binding: FragmentAddLightDialogBinding
    private lateinit var spinnerList : ArrayList<SidebarModel>
    private val sharedViewModel: AddRoomViewModel by activityViewModels()
    private val socketLightViewModel: SocketLightViewModel by activityViewModels()
    lateinit var sidebarModel: SidebarModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddLightDialogBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        observeData()
        initData()
        setClickListener()
        return binding.root
    }private fun observeData() {
        socketLightViewModel.receiveString.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                binding.tvTitle.text = it
            }
        }
    }private fun setClickListener() {
        binding.btnSave.setOnClickListener {
            if(binding.textInputEditText.text!!.isEmpty()){
                binding.textInputEditText.error= "This field is required"
            }
            else{
                sidebarModel.text = binding.textInputEditText.text.toString().trim()
                socketLightViewModel.addData(AddRoomModel(1,sidebarModel))
                binding.ciewFlipper.showNext()
                binding.tvDone.text = "${sidebarModel.text +" Created"}"
                Handler().postDelayed({
                    binding.ciewFlipper.showNext()
                    dismissDialog()
                },2000)
            }
        }
        binding.btnClose.setOnClickListener {
           socketLightViewModel.setDialogClose(true)
            dismiss()
        }
    }
    private fun dismissDialog() {
        Handler().postDelayed({
            socketLightViewModel.setDialogClose(true)
            dismiss()
        },1000)
    }private fun initData() {
        spinnerList = ArrayList()
        sidebarModel = SidebarModel("",0)
        spinnerList.add(SidebarModel("Light",R.drawable.ic_light))
        spinnerList.add(SidebarModel("Socket",R.drawable.kitchen))
        val adapter = CustomSpinnerAdapter(context, spinnerList)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItemText = (parent?.adapter as CustomSpinnerAdapter).getItemText(position)
                when(selectedItemText){
                    spinnerList.get(0).text ->
                    {
                        sidebarModel.img = R.drawable.light_yellow
                    }
                    spinnerList.get(1).text ->
                    {
                        sidebarModel.img = R.drawable.light_socket
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case where nothing is selected
            }
        }
    }

}