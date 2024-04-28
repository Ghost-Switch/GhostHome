package com.example.ghosthome.ghostHome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ghosthome.addroom.model.AddRoomModel

class SocketLightViewModel :ViewModel() {
    private val _data = MutableLiveData<String>()
    private val _dataRoomModel = MutableLiveData<AddRoomModel>()
    private val _updateDataRoomModel = MutableLiveData<AddRoomModel>()
    private val _position = MutableLiveData<Int>()
    private val _updatePosition = MutableLiveData<Int>()


    private val _dismiss = MutableLiveData<Boolean>()
    val receiveString: LiveData<String> = _data
    val dataRoomModel: LiveData<AddRoomModel> = _dataRoomModel
    val updateDataRoomModel: LiveData<AddRoomModel> = _updateDataRoomModel
    val isDismiss: LiveData<Boolean> = _dismiss
    val position: LiveData<Int> = _position
    val updatePosition: LiveData<Int> = _updatePosition
    public var positionValue = -1
    public var updatePositionValue = -1
    fun addData(newData: AddRoomModel) {
        _dataRoomModel.value = newData
    }

    fun deleteRoom(pos: Int) {
        _position.value = positionValue
    }
    fun updateRoom(newData: AddRoomModel) {
        _updateDataRoomModel.value = newData
    }
    init {
        _dismiss.value = false
        _position.value = -1
    }
    fun addString(newData: String) {
        _data.value = newData
    }
    fun setDialogClose(newData: Boolean) {
        _dismiss.value = newData
    }
}