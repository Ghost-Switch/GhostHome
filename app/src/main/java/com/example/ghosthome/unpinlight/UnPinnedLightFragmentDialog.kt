package com.example.ghosthome.unpinlight

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.GONE
import android.view.View.OnClickListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.ghosthome.addroom.model.AddRoomModel
import com.example.ghosthome.base.BaseDialogFragment
import com.example.ghosthome.databinding.FragmentUnPinnedLighetDialogBinding
import com.example.ghosthome.ghostHome.viewmodel.SocketLightViewModel
import com.example.ghosthome.shared.Utils

class UnPinnedLightFragmentDialog : BaseDialogFragment<FragmentUnPinnedLighetDialogBinding>(), OnClickListener {
    private val socketLightViewModel: SocketLightViewModel by activityViewModels()
    lateinit var model : AddRoomModel
    lateinit var utils: Utils
    override fun setupView() {

        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        utils = Utils()
        val action : UnPinnedLightFragmentDialogArgs by navArgs()
        model = action.argModel
        binding.pinLayout.tvTitle.text = "Enter security Pin to UnPin"
        binding.pinLayout.tvSubTitle.text = model.model.text
        binding.tvSubTitle.text = "you are removing ${model.model.text} from pinned devices"
        binding.tvTitle.text = "UnPin ${model.model.text}?"
        binding.pinLayout.pinViewOtpCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString() // Get the OTP text from the PinView
                if(otp.length==6){
                    model!!.model.isLightPin = false
                    binding.pinLayout.root.visibility = GONE
                    utils.dismissKeyboard(requireView())
                    if (model.model.isLightPin == false) {
                        val message = "${model.model.text}, Living Room has been UnPinned"
                        utils.showSnackBar(message,requireView())
                    }
                    socketLightViewModel.updateRoom(model)
                    Handler().postDelayed({
                        dismiss()
                    },1000)
//                    socketLightViewModel.updateRoom(model)
//                    dismiss()
//                    socketLightViewModel.updateRoom()
                }
            }
        })
        binding.btnYes.setOnClickListener(this)
        binding.btnNo.setOnClickListener(this)

    }

    override fun observeData() {

    }

    override fun fetchData() {

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            binding.btnYes.id ->{
                binding.viewFlipper.showNext()
            }
            binding.btnNo.id ->{
                dismiss()
            }


        }
    }


}