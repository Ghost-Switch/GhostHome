package com.example.ghosthome.home.adapter.model

import android.os.Parcel
import android.os.Parcelable

data class SidebarModel(var text:String, var img: Int, var isLightPin: Boolean?, var isLightLock: Boolean?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeInt(img)
        parcel.writeValue(isLightPin)
        parcel.writeValue(isLightLock)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SidebarModel> {
        override fun createFromParcel(parcel: Parcel): SidebarModel {
            return SidebarModel(parcel)
        }

        override fun newArray(size: Int): Array<SidebarModel?> {
            return arrayOfNulls(size)
        }
    }
}