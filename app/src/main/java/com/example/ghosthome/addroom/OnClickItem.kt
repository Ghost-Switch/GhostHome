package com.example.ghosthome.addroom

interface OnClickItem{
    fun onclick(text:Int)
}

interface OnClickMenuItem{
    fun onClickMenu(pos:Int,id:String)

}

interface OnClickSocket{
    fun onClickItem(text:String)
}

interface OnClickAddButton{
    fun onClickAdd(text: String)
}