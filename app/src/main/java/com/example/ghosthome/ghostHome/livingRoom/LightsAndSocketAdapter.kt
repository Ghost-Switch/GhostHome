package com.example.ghosthome.ghostHome.livingRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.R
import com.example.ghosthome.databinding.CardviewLightBinding

class LightsAndSocketAdapter(
    var lightsAndSockets: List<LightsAndSockets>
) : RecyclerView.Adapter<LightsAndSocketAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: CardviewLightBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val layoutBinding = CardviewLightBinding.inflate(view, parent, false)
        return ViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = lightsAndSockets[position]
        val binding = holder.binding

        binding.textviewLight.text = currentItem.title
        binding.lightSwitch.isChecked = currentItem.status

    }

    override fun getItemCount(): Int {
        return lightsAndSockets.size
    }
}