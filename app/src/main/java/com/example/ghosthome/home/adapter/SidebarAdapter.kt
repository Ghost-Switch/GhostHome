package com.example.ghosthome.home.adapter

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
import com.example.ghosthome.databinding.SidebarItemLayoutBinding
import com.example.ghosthome.home.adapter.model.SidebarModel

class SidebarAdapter(
    private var list:List<SidebarModel>,
    var context: Context,
    private  var onClickItem: OnClickItem

): RecyclerView.Adapter<SidebarAdapter.ViewHolder>(){

    lateinit var binding: SidebarItemLayoutBinding
    private var isTextVisible: Boolean= true
    private var selectedItem = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SidebarAdapter.ViewHolder {
        binding = SidebarItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SidebarAdapter.ViewHolder, position: Int) {
        var model:SidebarModel = list.get(position);
        holder.binding.ivSidebar.setImageResource(model.img!!)
        holder.binding.tvSidebar.text = model.text


        holder.binding.tvSidebar.visibility = if (isTextVisible) View.VISIBLE else View.GONE

        holder.binding.cv.setOnClickListener {
            val previousSelectedItem = selectedItem
            selectedItem = position
            notifyItemChanged(previousSelectedItem)
            notifyItemChanged(selectedItem)
            onClickItem.onclick(position)

        }

        val isSelected = position == selectedItem
        val colorBackground = if (isSelected) R.color.button_clicked_color else R.color.light_gray_f2
        val colorText = if (isSelected) R.color.white else R.color.menu_icon_color

        holder.binding.cv.setCardBackgroundColor(ContextCompat.getColor(context, colorBackground))
        holder.binding.tvSidebar.setTextColor(ContextCompat.getColor(context, colorText))
        holder.binding.ivSidebar.setColorFilter(ContextCompat.getColor(context, colorText))
    }
    fun setTextVisibility(visibility: Boolean) {
        isTextVisible = visibility
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    inner class ViewHolder(val binding: SidebarItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)


}
