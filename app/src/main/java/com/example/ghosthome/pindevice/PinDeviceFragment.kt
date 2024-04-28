package com.example.ghosthome.pindevice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.R
import com.example.ghosthome.addroom.OnClickItem
import com.example.ghosthome.addroom.OnClickMenuItem
import com.example.ghosthome.addroom.model.AddRoomModel
import com.example.ghosthome.base.BaseFragment
import com.example.ghosthome.databinding.FragmentPinDeviceBinding
import com.example.ghosthome.databinding.FragmentPinLightDialogBinding
import com.example.ghosthome.ghostHome.adapter.AddRoomSocketMultiViewAdapter
import com.example.ghosthome.ghostHome.viewmodel.SocketLightViewModel
import com.example.ghosthome.home.adapter.model.SidebarModel
import com.example.ghosthome.shared.Utils

class PinDeviceFragment : BaseFragment<FragmentPinDeviceBinding>(), OnClickItem, OnClickMenuItem {
    private val socketLightViewModel: SocketLightViewModel by activityViewModels()
    lateinit var list: ArrayList<AddRoomModel>
    private lateinit var recyclerView: RecyclerView
    lateinit var roomAdapter: AddRoomSocketMultiViewAdapter
    lateinit var utils: Utils
    override fun setupView() {
        list = ArrayList<AddRoomModel>()
        recyclerView = binding.rv
//        socketLightViewModel.addData(AddRoomModel(1, SidebarModel("Home", R.drawable.soclet_icon,false,false)))
        roomAdapter = AddRoomSocketMultiViewAdapter(list, context, this, this)
        recyclerView.layoutManager = GridLayoutManager(context, 4)
        recyclerView.adapter = roomAdapter
    }

    override fun observeData() {
        socketLightViewModel.dataRoomModel.observe(viewLifecycleOwner) {
            it
            if(it.model.isLightPin == true){
                roomAdapter.addCardItem(it)
            }
            else{

            }

        }

    }

    override fun fetchData() {

    }

    override fun onclick(text: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickMenu(pos: Int, id: String, model: AddRoomModel?) {
        TODO("Not yet implemented")
    }

}