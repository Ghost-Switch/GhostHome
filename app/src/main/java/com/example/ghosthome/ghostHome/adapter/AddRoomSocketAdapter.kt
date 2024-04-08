package com.example.ghosthome.ghostHome.adapter

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
import com.example.ghosthome.addroom.OnClickSocket
import com.example.ghosthome.databinding.CardviewLightBinding
import com.example.ghosthome.databinding.SidebarItemLayoutBinding
import com.example.ghosthome.databinding.SocketCardLayoutBinding
import com.example.ghosthome.home.adapter.model.SidebarModel

class AddRoomSocketAdapter(
    private var list:ArrayList<String>,
    var context: Context?,
    var onClickSocket: OnClickSocket
): RecyclerView.Adapter<AddRoomSocketAdapter.ViewHolder>(){
    lateinit var binding: SocketCardLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddRoomSocketAdapter.ViewHolder {
        binding = SocketCardLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddRoomSocketAdapter.ViewHolder, position: Int) {
        var txt:String = list.get(position)
        holder.binding.tvSocket.text = txt
        holder.binding.cvSocket.setOnClickListener {
            onClickSocket.onClickItem(txt)
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    inner class ViewHolder(val binding: SocketCardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}



