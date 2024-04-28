package com.example.ghosthome.addroom.model

import android.os.Parcel
import android.os.Parcelable
import com.example.ghosthome.home.adapter.model.SidebarModel
import java.io.Serializable


data class AddRoomModel(var type:Int, var model:SidebarModel) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        TODO("model")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddRoomModel> {
        override fun createFromParcel(parcel: Parcel): AddRoomModel {
            return AddRoomModel(parcel)
        }

        override fun newArray(size: Int): Array<AddRoomModel?> {
            return arrayOfNulls(size)
        }
    }
}



