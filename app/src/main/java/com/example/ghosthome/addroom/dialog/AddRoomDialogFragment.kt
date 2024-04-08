package com.example.ghosthome.addroom.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.ghosthome.R
import com.example.ghosthome.addroom.adapter.CustomSpinnerAdapter
import com.example.ghosthome.addroom.model.AddRoomModel
import com.example.ghosthome.addroom.viewmodel.AddRoomViewModel
import com.example.ghosthome.databinding.FragmentAddRoomDialogBinding
import com.example.ghosthome.home.adapter.model.SidebarModel

class AddRoomDialogFragment : DialogFragment() {
    lateinit var binding: FragmentAddRoomDialogBinding
    private lateinit var spinnerList : ArrayList<SidebarModel>
    private val sharedViewModel: AddRoomViewModel by activityViewModels()
    lateinit var sidebarModel: SidebarModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddRoomDialogBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initData()
        setClickListener()

        return binding.root
    }

    private fun setClickListener() {
        binding.btnSave.setOnClickListener {
            if(binding.textInputEditText.text!!.isEmpty()){
                binding.textInputEditText.error= "This field is required"
            }
            else{
                sidebarModel.text = binding.textInputEditText.text.toString().trim()
                sharedViewModel.updateData(AddRoomModel(1,sidebarModel))
                binding.cvDialog.visibility = View.GONE
                binding.layoutDone.visibility = View.VISIBLE
                Handler().postDelayed({
                dismiss()
                },2000)
            }
        }
    }

    private fun initData() {
        spinnerList = ArrayList()
        sidebarModel = SidebarModel("",0)

        spinnerList.add(SidebarModel("Living Room",R.drawable.living_rrom))
        spinnerList.add(SidebarModel("Kitchen",R.drawable.kitchen))
        spinnerList.add(SidebarModel("Room",R.drawable.ic_rooms))
        spinnerList.add(SidebarModel("Other",R.drawable.others))
        val adapter = CustomSpinnerAdapter(context, spinnerList)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItemText = (parent?.adapter as CustomSpinnerAdapter).getItemText(position)
                when(selectedItemText){
                    spinnerList.get(0).text ->
                    {
                        sidebarModel.img = R.drawable.add__living_room
                    }
                    spinnerList.get(1).text ->
                    {
                        sidebarModel.img = R.drawable.add_kitchen
                    }
                    spinnerList.get(2).text ->
                    {
                        sidebarModel.img = R.drawable.add_room
                    }
                    spinnerList.get(3).text ->
                    {
                        sidebarModel.img = R.drawable.add__living_room
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case where nothing is selected
            }
        }
    }

}