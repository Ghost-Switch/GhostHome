package com.example.ghosthome.ui.addroom.ghostHome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.ui.addroom.addroom.OnClickSocket
import com.example.ghosthome.databinding.SocketCardLayoutBinding

class AddRoomSocketAdapter(
    private var list:ArrayList<String>,
    var context: Context?,
    var onClickSocket: OnClickSocket
): RecyclerView.Adapter<AddRoomSocketAdapter.ViewHolder>(){
    lateinit var binding: SocketCardLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = SocketCardLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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



