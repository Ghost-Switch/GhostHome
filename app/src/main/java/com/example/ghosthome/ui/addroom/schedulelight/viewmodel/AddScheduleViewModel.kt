package com.example.ghosthome.ui.addroom.schedulelight.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ghosthome.ui.addroom.model.AddRoomModel
import com.example.ghosthome.ui.addroom.schedulelight.model.ScheduleTimeModel

class AddScheduleViewModel : ViewModel() {

    private val _data = MutableLiveData<ScheduleTimeModel>()
    private val _argument = MutableLiveData<String>()
    private val _deleteData = MutableLiveData<ScheduleTimeModel>()
    private val _isClickAdd = MutableLiveData<Boolean>()
    private val _isDismiss = MutableLiveData<Boolean>()
    private val _position = MutableLiveData<Int>()
    private var positionValue = -1
    val dataList: LiveData<ScheduleTimeModel> = _data
    val argumentObserver: LiveData<String> = _argument
    val deleteFromList: LiveData<ScheduleTimeModel> = _deleteData
    val isClickAdd: LiveData<Boolean> = _isClickAdd
    val position: LiveData<Int> = _position

    fun updateData(newData: ScheduleTimeModel) {
        _data.value = newData
    }
    fun setArgument(newData: String) {
        _argument.value = newData
    }

    fun clickedAdd(flag: Boolean) {
        _isClickAdd.value = flag
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

    fun addData(newData: ScheduleTimeModel) {
        _data.value = newData

    }
}