package com.example.ghosthome.ui.addroom.addroom

import android.hardware.SensorManager
import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.R
import com.example.ghosthome.ui.addroom.adapter.MultiViewAdapter
import com.example.ghosthome.ui.addroom.model.AddRoomModel
import com.example.ghosthome.ui.addroom.viewmodel.AddRoomViewModel
import com.example.ghosthome.databinding.FragmentAddRoomBinding
import com.example.ghosthome.ui.addroom.adapter.model.SidebarModel

class AddRoomFragment : Fragment(), OnClickItem, OnClickMenuItem {

    lateinit var binding:FragmentAddRoomBinding
    lateinit var roomAdapter: MultiViewAdapter
    private lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<AddRoomModel>
    private val sharedViewModel: AddRoomViewModel by activityViewModels()
    private lateinit var sensorManager: SensorManager
    private lateinit var display: Display
    private var count: Int = 4
    private fun observe() {

        sharedViewModel.dataList.observe(viewLifecycleOwner){it
            roomAdapter.addCardItem(it)
        }
        sharedViewModel.position.observe(viewLifecycleOwner){it
            if (it != -1){
                roomAdapter.deleteItem(it)
            }
            else{

            }
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRoomBinding.inflate(layoutInflater)
        observe()
        initData()
        display = requireActivity().windowManager.defaultDisplay
        return  binding.root
    }

    private fun initData() {
        list = ArrayList<AddRoomModel>()
        recyclerView = binding.rv
        sharedViewModel.addData(AddRoomModel(2, SidebarModel("Home",R.drawable.add_room)))
        roomAdapter = MultiViewAdapter(list,context,this,this)
        recyclerView.layoutManager = GridLayoutManager(context,count)
        recyclerView.adapter = roomAdapter
    }
    override fun onclick(pos: Int) {
//        roomAdapter.addCardItem(AddRoomModel(1,SidebarModel("Home",R.drawable.ic_home)));
        findNavController().navigate(R.id.action_addRoomFragment_to_addRoomDialogFragment)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Remove the observer when the view is destroyed
        sharedViewModel.dataList.removeObservers(viewLifecycleOwner)
    }

    override fun onClickMenu(pos: Int,id:String) {
//        sharedViewModel.deleteRoom(pos)
        sharedViewModel.positionValue = pos
        findNavController().navigate(R.id.action_addRoomFragment_to_deleteConfirmationDialogFragment)
//        roomAdapter.deleteItem(pos)
    }





}