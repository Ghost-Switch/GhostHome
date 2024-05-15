package com.example.ghosthome.ui.addroom.ghostHome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ghosthome.ui.addroom.model.AddRoomModel

class SocketLightViewModel :ViewModel() {
    private val _data = MutableLiveData<String>()
    private val _dataRoomModel = MutableLiveData<AddRoomModel>()
    private val _position = MutableLiveData<Int>()


    private val _dismiss = MutableLiveData<Boolean>()
    val receiveString: LiveData<String> = _data
    val dataRoomModel: LiveData<AddRoomModel> = _dataRoomModel
    val isDismiss: LiveData<Boolean> = _dismiss
    val position: LiveData<Int> = _position
    public var positionValue = -1
    fun addData(newData: AddRoomModel) {
        _dataRoomModel.value = newData
    }

    fun deleteRoom(pos: Int) {
        _position.value = positionValue
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