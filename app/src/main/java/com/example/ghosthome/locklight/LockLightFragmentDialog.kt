package com.example.ghosthome.locklight

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.ghosthome.R
import com.example.ghosthome.addroom.model.AddRoomModel
import com.example.ghosthome.base.BaseDialogFragment
import com.example.ghosthome.databinding.FragmentLockLightDialogBinding
import com.example.ghosthome.ghostHome.viewmodel.SocketLightViewModel
import com.example.ghosthome.shared.Utils


class LockLightFragmentDialog : BaseDialogFragment<FragmentLockLightDialogBinding>() {
    lateinit var addRoomModel: AddRoomModel
    private val socketLightViewModel: SocketLightViewModel by activityViewModels()
    lateinit var utils: Utils
    override fun setupView() {
        utils= Utils()
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val argument : LockLightFragmentDialogArgs by navArgs()
        addRoomModel = argument.argModel
        if(addRoomModel.model.isLightLock == true){
            binding.pinView.tvHeader.text = "UnLock "+addRoomModel.model.text
            binding.pinView.tvTitle.text = "Enter Lock Pin to UnLock"
            binding.pinView.tvSubTitle.text = addRoomModel.model.text
            binding.tvLockName.text = addRoomModel.model.text  +" "+  resources.getString(R.string.has_been_unlocked)

        }
        else{

            binding.pinView.tvHeader.text = "Lock "+addRoomModel.model.text
            binding.pinView.tvTitle.text = "Enter Lock Pin to Lock"
            binding.pinView.tvSubTitle.text = addRoomModel.model.text
            binding.tvLockName.text = addRoomModel.model.text +" "+ resources.getString(R.string.has_been_locked)

        }

        binding.pinView.pinViewOtpCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString() // Get the OTP text from the PinView
                if(otp.length==6){
                    utils.dismissKeyboard(requireView())
                    binding.viewFlipper.showNext()


                }
            }
        })
        binding.btnContinue.setOnClickListener{
            binding.secondLayout.visibility= View.GONE
            addRoomModel.model.isLightLock = !addRoomModel.model.isLightLock!!
            val message = if (addRoomModel.model.isLightLock == true) {
                     "${addRoomModel.model.text}, Living Room has been Locked"
                } else {
                     "${addRoomModel.model.text}, Living Room has been UnLocked"
                }
                utils.showSnackBar(message,requireView())
                socketLightViewModel.updateRoom(addRoomModel)
            Handler().postDelayed({
                dismiss()
            },1000)

        }
    }

    override fun observeData() {
    }
    override fun fetchData() {

    }


}