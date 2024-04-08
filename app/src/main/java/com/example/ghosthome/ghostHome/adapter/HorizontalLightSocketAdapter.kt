package com.example.ghosthome.ghostHome.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets.Side
import android.view.textclassifier.TextClassificationContext
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.R
import com.example.ghosthome.addroom.OnClickAddButton
import com.example.ghosthome.addroom.OnClickItem
import com.example.ghosthome.databinding.CardviewLightBinding
import com.example.ghosthome.databinding.HorizontalLightLayoutBinding
import com.example.ghosthome.databinding.SidebarItemLayoutBinding
import com.example.ghosthome.ghostHome.model.LightSocketModel
import com.example.ghosthome.home.adapter.model.SidebarModel

class HorizontalLightSocketAdapter(
    private var list: ArrayList<LightSocketModel>,
    var context: Context?,
    var onClickAddButton: OnClickAddButton


): RecyclerView.Adapter<HorizontalLightSocketAdapter.ViewHolder>(){

    lateinit var binding: HorizontalLightLayoutBinding
    var text:String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalLightSocketAdapter.ViewHolder {
        binding = HorizontalLightLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalLightSocketAdapter.ViewHolder, position: Int) {
        var model:LightSocketModel = list.get(position)
        holder.binding.ivIcon.setImageResource(model.img)
        holder.binding.tvTitle.text = model.text

        holder.binding.toggle.setOnCheckedChangeListener { _, isChecked ->
            model.isChecked = isChecked
        }
        holder.binding.btnAdd.setOnClickListener {
            val isChecked = holder.binding.toggle.isChecked
            if (!isChecked) {
                Toast.makeText(context, "Please switch on ${model.text} first", Toast.LENGTH_SHORT).show()
            } else {
                onClickAddButton.onClickAdd(model.text)
            }
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    inner class ViewHolder(val binding: HorizontalLightLayoutBinding) : RecyclerView.ViewHolder(binding.root)


}
