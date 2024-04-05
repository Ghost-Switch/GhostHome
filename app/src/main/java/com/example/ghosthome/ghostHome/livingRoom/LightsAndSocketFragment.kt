package com.example.ghosthome.ghostHome.livingRoom

import android.hardware.SensorManager
import android.os.Bundle
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.R
import com.example.ghosthome.addroom.OnClickItem
import com.example.ghosthome.addroom.OnClickMenuItem
import com.example.ghosthome.addroom.model.AddRoomModel
import com.example.ghosthome.addroom.viewmodel.AddRoomViewModel
import com.example.ghosthome.databinding.FragmentAllActiveBinding
import com.example.ghosthome.databinding.FragmentLightsAndSocketBinding
import com.example.ghosthome.ghostHome.adapter.AddRoomSocketMultiViewAdapter
import com.example.ghosthome.ghostHome.viewmodel.SocketLightViewModel
import com.example.ghosthome.home.adapter.model.SidebarModel


class LightsAndSocketFragment : Fragment(), OnClickItem, OnClickMenuItem {

    private lateinit var binding: FragmentLightsAndSocketBinding
    lateinit var roomAdapter: AddRoomSocketMultiViewAdapter
    private lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<AddRoomModel>
    private val sharedViewModel: AddRoomViewModel by activityViewModels()
    private val socketLightViewModel: SocketLightViewModel by activityViewModels()
    private lateinit var sensorManager: SensorManager
    private lateinit var display: Display
    var count: Int = 4

    private fun observe() {
        socketLightViewModel.dataRoomModel.observe(viewLifecycleOwner) {
            it
            roomAdapter.addCardItem(it)
        }
        socketLightViewModel.position.observe(viewLifecycleOwner) {
            it
            if (it != -1) {
                roomAdapter.deleteItem(it)
            } else {
            }
        }
        socketLightViewModel.receiveString.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                Toast.makeText(context, "IF String :$it", Toast.LENGTH_SHORT).show()
            } else {
//                Toast.makeText(context, "String :$it", Toast.LENGTH_SHORT).show()
//                findNavController().navigate(R.id.action_ghostHomeActivity_to_addLightDialogFragment2)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLightsAndSocketBinding.inflate(layoutInflater)
        observe()
        initData()
        display = requireActivity().windowManager.defaultDisplay
        return binding.root
    }

    private fun initData() {
        list = ArrayList<AddRoomModel>()
        recyclerView = binding.rv
//        socketLightViewModel.addData(AddRoomModel(2, SidebarModel("Home", R.drawable.light_socket)))
        roomAdapter = AddRoomSocketMultiViewAdapter(list, context, this, this)
        recyclerView.layoutManager = GridLayoutManager(context, count)
        recyclerView.adapter = roomAdapter

    }

    override fun onclick(pos: Int) {

//        findNavController().navigate(R.id.action_ghostHomeActivity_to_addLightDialogFragment2)
        findNavController().navigate(R.id.action_ghostHomeActivity_to_addRoomSocketDialogFragment)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Remove the observer when the view is destroyed

        socketLightViewModel.dataRoomModel.removeObservers(viewLifecycleOwner)
    }

    override fun onClickMenu(pos: Int) {
//        sharedViewModel.deleteRoom(pos)
        socketLightViewModel.positionValue = pos
        findNavController().navigate(R.id.action_ghostHomeActivity_to_deleteConfirmationDialogFragment)
//        roomAdapter.deleteItem(pos)
    }


}