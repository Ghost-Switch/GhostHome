package com.example.ghosthome.addroom.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets.Side
import android.view.textclassifier.TextClassificationContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.R
import com.example.ghosthome.addroom.OnClickItem
import com.example.ghosthome.databinding.CardviewLightBinding
import com.example.ghosthome.databinding.SidebarItemLayoutBinding
import com.example.ghosthome.home.adapter.model.SidebarModel

class AddRoomAdapter(
    private var list:ArrayList<SidebarModel>,
    var context: Context?,


): RecyclerView.Adapter<AddRoomAdapter.ViewHolder>(){

    lateinit var binding: CardviewLightBinding
    private var isTextVisible: Boolean= true
    private var selectedItem = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddRoomAdapter.ViewHolder {
        binding = CardviewLightBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddRoomAdapter.ViewHolder, position: Int) {
        var model:SidebarModel = list.get(position)

    }
    fun setTextVisibility(visibility: Boolean) {
        isTextVisible = visibility
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    inner class ViewHolder(val binding: CardviewLightBinding) : RecyclerView.ViewHolder(binding.root)
    fun addCardItem(cardItem: SidebarModel) {
        list.add(cardItem)
        notifyDataSetChanged()
    }

}
