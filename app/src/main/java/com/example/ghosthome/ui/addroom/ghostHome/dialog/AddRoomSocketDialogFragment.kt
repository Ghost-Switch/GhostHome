package com.example.ghosthome.ui.addroom.ghostHome.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ghosthome.R
import com.example.ghosthome.ui.addroom.addroom.OnClickAddButton
import com.example.ghosthome.ui.addroom.addroom.OnClickSocket
import com.example.ghosthome.databinding.FragmentAddRoomSocketDialogBinding
import com.example.ghosthome.ui.addroom.ghostHome.adapter.AddRoomSocketAdapter
import com.example.ghosthome.ui.addroom.ghostHome.adapter.HorizontalLightSocketAdapter
import com.example.ghosthome.ui.addroom.ghostHome.model.LightSocketModel
import com.example.ghosthome.ui.addroom.ghostHome.viewmodel.SocketLightViewModel

class AddRoomSocketDialogFragment : DialogFragment(), OnClickSocket, OnClickAddButton {
    lateinit var binding: FragmentAddRoomSocketDialogBinding
    private val sharedViewModel: SocketLightViewModel by activityViewModels()
    lateinit var list: ArrayList<String>
    private lateinit var lightSocketModel: ArrayList<LightSocketModel>
    lateinit var adapter: AddRoomSocketAdapter
    private lateinit var horizontalAdapter: HorizontalLightSocketAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRoomSocketDialogBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initData()
        observeData()
        setOnClick()
        return binding.root
    }
    private fun observeData() {

        sharedViewModel.receiveString.observe(viewLifecycleOwner){
            binding.tvSection.text = it
        }
        sharedViewModel.isDismiss.observe(viewLifecycleOwner){
            if (it){
                dismiss()
                sharedViewModel.setDialogClose(false) } }
    }
    private fun setOnClick() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
    private fun initData() {
        // Set Section Adapter
        list = ArrayList()
        for (i in 1..6) {
            list.add("Section $i")
        }
        adapter = AddRoomSocketAdapter(list, context, this)
        binding.rvSocket.layoutManager = GridLayoutManager(context, 3)
        binding.rvSocket.adapter = adapter
        // Set Horizontal Adapter
        lightSocketModel = ArrayList()
        lightSocketModel.add(LightSocketModel(R.drawable.ic_light, "Light"))
        lightSocketModel.add(LightSocketModel(R.drawable.ic_light, "Light"))
        lightSocketModel.add(LightSocketModel(R.drawable.kitchen, "Socket"))
        lightSocketModel.add(LightSocketModel(R.drawable.kitchen, "Socket"))
        horizontalAdapter = HorizontalLightSocketAdapter(lightSocketModel, context, this)
        binding.rvLightSocket.layoutManager = LinearLayoutManager(context)
        binding.rvLightSocket.adapter = horizontalAdapter
    }

    override fun onClickItem(text: String) {
        sharedViewModel.addString(text)
        binding.viewFlipper.showNext()
        Handler().postDelayed(
            {
                binding.layoutInnerFlipper.showNext()
            },
            1000
        )
    }
    override fun onClickAdd(pos: String) {
        sharedViewModel.addString(pos)
//        dialog!!.dismiss()
//        findNavController().navigateUp()
        findNavController().navigate(R.id.action_addRoomSocketDialogFragment_to_addLightDialogFragment2)
//
//        Handler().postDelayed({
//            if (findNavController().currentDestination?.id == R.id.addRoomSocketDialogFragment) {
//
//                findNavController().navigateUp()
//            }
//        },2000)

    }


}