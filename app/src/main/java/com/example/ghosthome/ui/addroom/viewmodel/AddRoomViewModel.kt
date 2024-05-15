package com.example.ghosthome.ui.addroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ghosthome.ui.addroom.model.AddRoomModel

class AddRoomViewModel : ViewModel() {

    private val _data = MutableLiveData<AddRoomModel>()
    private val _position = MutableLiveData<Int>()
    public var positionValue = -1
    val dataList: LiveData<AddRoomModel> = _data
    val position: LiveData<Int> = _position

    fun updateData(newData: AddRoomModel) {
        _data.value = newData

    }

    init {
        _position.value = -1
    }

    fun deleteRoom(pos: Int) {
        _position.value = positionValue
    }
//    init {
//        // Initialize with empty list
//        _data.value = empty
//    }

    fun addData(newData: AddRoomModel) {
        _data.value = newData
    }
}