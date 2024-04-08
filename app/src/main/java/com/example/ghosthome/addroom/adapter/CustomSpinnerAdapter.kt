package com.example.ghosthome.addroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.ghosthome.R
import com.example.ghosthome.home.adapter.model.SidebarModel
import com.google.android.material.divider.MaterialDivider

class CustomSpinnerAdapter (context: Context?,
                            private val items: List<SidebarModel>
) : ArrayAdapter<SidebarModel>(context!!, R.layout.dropdown_layout, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }
    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.dropdown_layout, parent, false)
        val icon = view.findViewById<ImageView>(R.id.icon)
        val text = view.findViewById<TextView>(R.id.text)
        val divider = view.findViewById<MaterialDivider>(R.id.divider)
        val item = items[position]
        icon.setImageResource(item.img)
        text.text = item.text
        if (position==3){
            divider.visibility = View.GONE
        }

        return view
    }
    fun getItemText(position: Int): String {
        return items[position].text
    }
}