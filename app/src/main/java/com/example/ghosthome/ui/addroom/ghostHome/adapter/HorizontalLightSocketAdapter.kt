package com.example.ghosthome.ui.addroom.ghostHome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.ui.addroom.addroom.OnClickAddButton
import com.example.ghosthome.databinding.HorizontalLightLayoutBinding
import com.example.ghosthome.ui.addroom.ghostHome.model.LightSocketModel

class HorizontalLightSocketAdapter(
    private var list: ArrayList<LightSocketModel>,
    var context: Context?,
    var onClickAddButton: OnClickAddButton


): RecyclerView.Adapter<HorizontalLightSocketAdapter.ViewHolder>(){

    lateinit var binding: HorizontalLightLayoutBinding
    var text:String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HorizontalLightLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model: LightSocketModel = list.get(position)
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
