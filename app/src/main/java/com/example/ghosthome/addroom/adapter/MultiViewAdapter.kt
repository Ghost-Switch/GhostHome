package com.example.ghosthome.addroom.adapter

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.ghosthome.R
import com.example.ghosthome.addroom.OnClickItem
import com.example.ghosthome.addroom.OnClickMenuItem
import com.example.ghosthome.addroom.model.AddRoomModel
import com.example.ghosthome.databinding.AddItemLayoutBinding
import com.example.ghosthome.databinding.AddRoomCardBinding
import com.example.ghosthome.databinding.CardviewLightBinding

class MultiViewAdapter(
    private var list:ArrayList<AddRoomModel>,
    var context: Context?,
    private var onClickItem:OnClickItem,
    private var onClickMenuItem: OnClickMenuItem


): Adapter<RecyclerView.ViewHolder>(){

    lateinit var binding: AddRoomCardBinding
    private lateinit var binAddItemLayoutBinding: AddItemLayoutBinding
    private var isTextVisible: Boolean= true
    private var selectedItem = 0

    companion object {
        private const val VIEW_TYPE_ONE = 1
        private const val VIEW_TYPE_TWO = 2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ONE -> {
                binding = AddRoomCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return  TypeOneViewHolder(binding)
            }
            VIEW_TYPE_TWO -> {
                binAddItemLayoutBinding = AddItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return TypeTwoViewHolder(binAddItemLayoutBinding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var model:AddRoomModel = list.get(position)

        when (holder.itemViewType) {
            VIEW_TYPE_ONE -> {
                val typeOneViewHolder = holder as TypeOneViewHolder
                typeOneViewHolder.binding.ivRoom.setImageResource(model.model.img)
                typeOneViewHolder.binding.tvRoom.text = model.model.text
                typeOneViewHolder.binding.btnMore.setOnClickListener {
                    showPopupMenu(it,position)


                }
            }
            VIEW_TYPE_TWO -> {
                val typeTwoViewHolder = holder as TypeTwoViewHolder
                typeTwoViewHolder.binAddItemLayoutBinding.addBtn.setOnClickListener {
                    onClickItem.onclick(position)
                }

            }
        }
    }


        private fun showPopupMenu(v:View,pos:Int) {
            // Creating a PopupMenu
//        val popupMenu = PopupMenu(requireContext(), requireView())
            val wrapper: Context = ContextThemeWrapper(context, R.style.PopupMenu)
            val popup = android.widget.PopupMenu(wrapper, v, Gravity.BOTTOM)
            popup.inflate(R.menu.add_room_menu)
            val lastMenuItem = popup.menu.getItem(popup.menu.size() - 1)

            // Set the text color of the last menu item to red
            lastMenuItem.title = SpannableString(lastMenuItem.title).apply {
                setSpan(ForegroundColorSpan(ContextCompat.getColor(context!!, R.color.button_clicked_color)), 0, length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }


            // Inflating the menu from XML resource
//        popupMenu.inflate(R.menu.add_room_menu)

            // Adding click listener to menu items
            popup.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.delete -> {
                        onClickMenuItem.onClickMenu(pos)
                        true // Return true to consume the event
                    }
                    R.id.add_light -> {
                        // Handle item 2 click
                        true // Return true to consume the event
                    }
                    // Handle other menu items if needed
                    else -> false // Return false for items not handled
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popup.setForceShowIcon(true)
            }

            // Showing the popup menu
            popup.show()
        }


    override fun getItemCount(): Int {
        return  list.size
    }

    inner class ViewHolder(val binding: CardviewLightBinding) : RecyclerView.ViewHolder(binding.root)
    fun addCardItem(cardItem: AddRoomModel) {
        list.add(0,cardItem)
        notifyDataSetChanged()
    }
    fun deleteItem(pos: Int) {
        list.removeAt(pos)
        notifyDataSetChanged()
    }


    inner class TypeOneViewHolder(var binding: AddRoomCardBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            this.binding = binding
        }
    }
    inner class TypeTwoViewHolder(val binAddItemLayoutBinding: AddItemLayoutBinding) : RecyclerView.ViewHolder(binAddItemLayoutBinding.root)

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return when (item.type) {
            VIEW_TYPE_ONE -> VIEW_TYPE_ONE
            VIEW_TYPE_TWO -> VIEW_TYPE_TWO
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

}
