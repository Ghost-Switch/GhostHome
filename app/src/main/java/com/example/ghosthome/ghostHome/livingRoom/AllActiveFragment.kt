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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.GhostHomeActivityDirections

import com.example.ghosthome.R
import com.example.ghosthome.addroom.OnClickItem
import com.example.ghosthome.addroom.OnClickMenuItem
import com.example.ghosthome.addroom.adapter.MultiViewAdapter
import com.example.ghosthome.addroom.model.AddRoomModel
import com.example.ghosthome.addroom.viewmodel.AddRoomViewModel
import com.example.ghosthome.databinding.FragmentAddRoomBinding
import com.example.ghosthome.databinding.FragmentAllActiveBinding
import com.example.ghosthome.ghostHome.adapter.AddRoomSocketMultiViewAdapter
import com.example.ghosthome.ghostHome.viewmodel.SocketLightViewModel
import com.example.ghosthome.home.adapter.model.SidebarModel
import com.example.ghosthome.schedulelight.ScheduleLightDialogFragment
import com.example.ghosthome.shared.Utils


class AllActiveFragment : Fragment(), OnClickItem, OnClickMenuItem {
    lateinit var binding: FragmentAllActiveBinding
    lateinit var roomAdapter: AddRoomSocketMultiViewAdapter
    private lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<AddRoomModel>
    private val sharedViewModel: AddRoomViewModel by activityViewModels()
    private val socketLightViewModel: SocketLightViewModel by activityViewModels()
    lateinit var utils: Utils

    private lateinit var display: Display
    private var count: Int = 4

    private fun observe() {
        socketLightViewModel.dataRoomModel.observe(viewLifecycleOwner) {
            it
            roomAdapter.addCardItem(it)
        }
        socketLightViewModel.position.observe(viewLifecycleOwner) {
            it
            if (it != -1) {
                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
                roomAdapter.deleteItem(it)
            } else {
            }
        }
        socketLightViewModel.updateDataRoomModel.observe(viewLifecycleOwner) {
            it
            if (it != null) {
                roomAdapter.updateItem(socketLightViewModel.updatePositionValue,it)
//
            }
            else {
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
        binding = FragmentAllActiveBinding.inflate(layoutInflater)
        utils = Utils()
        observe()
        initData()
        display = requireActivity().windowManager.defaultDisplay

        return binding.root
    }

    private fun initData() {
        list = ArrayList<AddRoomModel>()
        recyclerView = binding.rv
        socketLightViewModel.addData(AddRoomModel(1, SidebarModel("Home", R.drawable.soclet_icon,false,false)))
//        socketLightViewModel.addData(AddRoomModel(1, SidebarModel("Home", R.drawable.light_socket,false,false)))
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
//        socketLightViewModel.dataRoomModel.removeObservers(viewLifecycleOwner)
    }

    override fun onClickMenu(pos: Int,id: String,model: AddRoomModel?) {
//        sharedViewModel.deleteRoom(pos)
        when(id){
           resources.getString(R.string.remove_light) -> {
                socketLightViewModel.positionValue = pos
                findNavController().navigate(R.id.action_ghostHomeActivity_to_deleteConfirmationDialogFragment)
            }
            resources.getString(R.string.schedule_light) -> {
                val action = GhostHomeActivityDirections.actionGhostHomeActivityToScheduleLightDialogFragment(pos)
                findNavController().navigate(action)
            }
            resources.getString(R.string.timer) -> {
                val action = GhostHomeActivityDirections.actionGhostHomeActivityToScheduleLightDialogFragment(pos)
                findNavController().navigate(action)
            }
            resources.getString(R.string.pin_light) -> {
                socketLightViewModel.updatePositionValue = pos

//                socketLightViewModel.updateRoom(model)
                val action = model?.let {
                    GhostHomeActivityDirections.actionGhostHomeActivityToPinLightFragmentDialog11(
                        it
                    )
                }
                if (action != null) {
                    findNavController().navigate(action)
                }

            }
            resources.getString(R.string.unpin_light) -> {
                socketLightViewModel.updatePositionValue = pos


               val action = model?.let {
                   GhostHomeActivityDirections.actionGhostHomeActivityToUnPinnedLightFragmentDialog3(
                       it
                   )
               }
                if (action != null) {
                    findNavController().navigate(action)
                }

            }
            resources.getString(R.string.lock_light) -> {
                socketLightViewModel.updatePositionValue = pos

//                socketLightViewModel.updateRoom(model)
                val action = model?.let {
                    GhostHomeActivityDirections.actionGhostHomeActivityToLockLightFragmentDialog3(
                        it
                    )
                }
                if (action != null) {
                    findNavController().navigate(action)
                }

            }
            resources.getString(R.string.unlock_light) -> {
                socketLightViewModel.updatePositionValue = pos

                val action = model?.let {
                    GhostHomeActivityDirections.actionGhostHomeActivityToLockLightFragmentDialog3(
                        it
                    )
                }
                if (action != null) {
                    findNavController().navigate(action)
                }

            }
            resources.getString(R.string.lock_settings) -> {

                socketLightViewModel.updatePositionValue = pos

                val action = model?.let {
                    GhostHomeActivityDirections.actionGhostHomeActivityToForgotLockPinFragmentDialog(it)
                }
                if (action != null) {
                    findNavController().navigate(action)
                }

            }


        }

//        roomAdapter.deleteItem(pos)
    }


}