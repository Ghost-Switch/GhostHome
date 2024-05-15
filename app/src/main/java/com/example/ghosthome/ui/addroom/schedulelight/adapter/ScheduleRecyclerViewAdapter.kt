package com.example.ghosthome.ui.addroom.schedulelight.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ghosthome.R
import com.example.ghosthome.databinding.ScheduleItemLayoutBinding
import com.example.ghosthome.ui.addroom.schedulelight.model.ScheduleTimeModel


class ScheduleRecyclerViewAdapter(val data: ArrayList<ScheduleTimeModel>, val context: Context) :
    RecyclerView.Adapter<ScheduleRecyclerViewAdapter.MyViewHolder>() {

    lateinit var binding:ScheduleItemLayoutBinding

    inner class MyViewHolder(binding: ScheduleItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {
//        private val mTitle: TextView
//        var relativeLayout: RelativeLayout? = null

//        init {
//            mTitle = itemView.findViewById<TextView>(R.id.txtTitle)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ScheduleItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var scheduleTimeModel: ScheduleTimeModel = data.get(position)
        binding.endTime.text = scheduleTimeModel.endTime
        binding.startTime.text = scheduleTimeModel.startTime
        binding.tvType.text = scheduleTimeModel.type
        binding.relativeLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white))


    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: ScheduleTimeModel, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }
}