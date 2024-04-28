package com.example.ghosthome.ghostHome.adapter

import android.content.Context
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
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.ghosthome.R
import com.example.ghosthome.addroom.OnClickItem
import com.example.ghosthome.addroom.OnClickMenuItem
import com.example.ghosthome.addroom.model.AddRoomModel
import com.example.ghosthome.databinding.AddItemLayoutBinding
import com.example.ghosthome.databinding.CardviewLightBinding


class AddRoomSocketMultiViewAdapter(
    private var list:ArrayList<AddRoomModel>,
    var context: Context?,
    private var onClickItem:OnClickItem,
    private var onClickMenuItem: OnClickMenuItem


): Adapter<RecyclerView.ViewHolder>(){

    lateinit var binding: CardviewLightBinding
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
                binding = CardviewLightBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
                typeOneViewHolder.binding.lightIcon.setImageResource(model.model.img)
                typeOneViewHolder.binding.textviewLight.text = model.model.text
                binding.lightSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                    // Your code here to handle the switch state change
                    if (isChecked) {
                        // Switch is ON

                    } else {
                        // Switch is OFF
                    }
                })
                val isLightPin = model.model.isLightPin
                if (isLightPin == true){
                    typeOneViewHolder.binding.ivPin.visibility = VISIBLE
                }
                else{
                    typeOneViewHolder.binding.ivPin.visibility = GONE
                }
                if (model.model.isLightLock ==  true){
//                     binding.lightSwitch.trackDrawable.setTint(ContextCompat.getColor(context!!, R.color.switch_thrack_color))
//                    binding.lightSwitch.getThumbDrawable().setTint(ContextCompat.getColor(context!!, R.color.switch_thum_lock_color))
                    typeOneViewHolder.binding.ivLock.visibility = VISIBLE
                    typeOneViewHolder.binding.controlCardview.setCardBackgroundColor(context!!.resources.getColor(R.color.button_clicked_color))
                    typeOneViewHolder.binding.btnMore.setCardBackgroundColor(context!!.resources.getColor(R.color.e1000f))
                    typeOneViewHolder.binding.lightCardview.setCardBackgroundColor(context!!.resources.getColor(R.color.aa0000))
                }
                else{
//                    binding.lightSwitch.trackDrawable.setTint(ContextCompat.getColor(context!!, R.color.switch_thrack_color))
//                    binding.lightSwitch.getThumbDrawable().setTint(ContextCompat.getColor(context!!, R.color.switch_thum_color))
                    binding.lightSwitch.trackDrawable.setTint(ContextCompat.getColor(context!!, R.color.switch_thrack_color))
                    typeOneViewHolder.binding.controlCardview.setCardBackgroundColor(context!!.resources.getColor(R.color._4e4e4e))
                    typeOneViewHolder.binding.btnMore.setCardBackgroundColor(context!!.resources.getColor(R.color.gray7674))
                    typeOneViewHolder.binding.lightCardview.setCardBackgroundColor(context!!.resources.getColor(R.color._2f2f2f))
                    typeOneViewHolder.binding.ivLock.visibility = GONE
                }

                typeOneViewHolder.binding.btnMore.setOnClickListener {
                    showPopupMenu(it,position,model)
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


        private fun showPopupMenu(v: View, pos: Int, model:AddRoomModel) {
            // Creating a PopupMenu
            val wrapper: Context = ContextThemeWrapper(context, R.style.PopupMenu)
            val popup = android.widget.PopupMenu(wrapper, v, Gravity.BOTTOM)
            popup.inflate(R.menu.add_light_menu)
            val pinLightMenuItem = popup.menu.findItem(R.id.pin_light)
            val pinLockLightMenuItem = popup.menu.findItem(R.id.lock_light)
            pinLightMenuItem.title = if (model.model.isLightPin == true) {
                context!!.getString(R.string.unpin_light)
            } else {
                context!!.getString(R.string.pin_light)
            }
            pinLockLightMenuItem.title = if (model.model.isLightLock == true) {
                context!!.getString(R.string.unlock_light)
            } else {
                context!!.getString(R.string.lock_light)
            }
            val lastMenuItem = popup.menu.getItem(popup.menu.size() - 1)
            // Set the text color of the last menu item to red
            lastMenuItem.title = SpannableString(lastMenuItem.title).apply {
                setSpan(ForegroundColorSpan(ContextCompat.getColor(context!!, R.color.button_clicked_color)), 0, length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }

            // Adding click listener to menu items
            popup.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.delete -> {
                        onClickMenuItem.onClickMenu(pos,context!!.resources.getString(R.string.remove_light),null)
                        true // Return true to consume the event
                    }
                    R.id.add_light -> {
                        // Handle item 2 click
                        true // Return true to consume the event
                    }
                    R.id.schedule -> {
                        onClickMenuItem.onClickMenu(pos,context!!.resources.getString(R.string.schedule_light),null)
                        true // Return true to consume the event
                    }
                    R.id.lock_setting -> {
                        onClickMenuItem.onClickMenu(pos,context!!.resources.getString(R.string.lock_settings),model)
                        true // Return true to consume the event
                    }
                    R.id.pin_light -> {
                        val title = if (model.model.isLightPin == true) {
                            context!!.getString(R.string.unpin_light)
                        } else {
                            context!!.getString(R.string.pin_light)
                        }
                        onClickMenuItem.onClickMenu(pos,title,model)
                        true // Return true to consume the event
                    }
                    R.id.lock_light -> {
                        val title = if (model.model.isLightLock == true) {
                            context!!.getString(R.string.unlock_light)
                        } else {
                            context!!.getString(R.string.lock_light)
                        }
                        onClickMenuItem.onClickMenu(pos,title,model)
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
    fun updateItem(pos: Int,model:AddRoomModel) {
        list[pos] = model
        notifyDataSetChanged()
    }


    inner class TypeOneViewHolder(var binding: CardviewLightBinding) : RecyclerView.ViewHolder(binding.root){
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
