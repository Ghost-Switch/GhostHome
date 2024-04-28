package com.example.ghosthome.addroom

import com.example.ghosthome.addroom.model.AddRoomModel

interface OnClickItem{
    fun onclick(text:Int)
}

interface OnClickMenuItem{
    fun onClickMenu(pos:Int,id:String,model:AddRoomModel?)

}

interface OnClickSocket{
    fun onClickItem(text:String)
}

interface OnClickAddButton{
    fun onClickAdd(text: String)
}