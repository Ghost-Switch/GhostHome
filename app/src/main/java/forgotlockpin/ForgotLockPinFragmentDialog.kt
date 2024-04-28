package forgotlockpin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.ghosthome.R
import com.example.ghosthome.addroom.model.AddRoomModel
import com.example.ghosthome.base.BaseDialogFragment
import com.example.ghosthome.databinding.FragmentForgotLockPinDialogBinding
import com.example.ghosthome.ghostHome.viewmodel.SocketLightViewModel
import com.example.ghosthome.pinlight.PinLightFragmentDialogArgs
import com.example.ghosthome.shared.Utils

class ForgotLockPinFragmentDialog : BaseDialogFragment<FragmentForgotLockPinDialogBinding>(),OnClickListener{
//    private val socketLightViewModel: SocketLightViewModel by activityViewModels()
    lateinit var model: AddRoomModel
    lateinit var utils: Utils
    override fun setupView() {
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val action: PinLightFragmentDialogArgs by navArgs()
        utils = Utils()
        model = action.argModel
        initForgotPassword(model)
        initSetUpLockPin(model)
        initChangePin(model)
        binding.btnForgotPin.setOnClickListener(this)
        binding.btnSendOtp.setOnClickListener(this)
        binding.btnChangePin.setOnClickListener(this)
        binding.btnSetUp.setOnClickListener(this)
        binding.btnSendOtp.setOnClickListener(this)
        binding.changePinLayout.btnContinue.setOnClickListener(this)
        binding.newPinLayout.btnContinue.setOnClickListener(this)


        binding.forgotPinLayout.pinViewOtp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString() // Get the OTP text from the PinView
                if(otp.length==6){
                    utils.dismissKeyboard(requireView())
                    binding.forgotFlipper.showNext()
//                    socketLightViewModel.updateRoom()
                }
            }
        })
        binding.forgotPinRecovery.pinViewOtpCode.itemCount = 4
        binding.forgotPinRecovery.pinViewOtpCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString() // Get the OTP text from the PinView
                if(otp.length==4){
                    utils.dismissKeyboard(requireView())
                    dismiss()

//                    socketLightViewModel.updateRoom()
                }
            }
        })
        binding.setUpPinLayout.pinViewOtpCode.itemCount = 4
        binding.setUpPinLayout.pinViewOtpCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString() // Get the OTP text from the PinView
                if(otp.length==4){
                    utils.dismissKeyboard(requireView())
                    binding.setUpFlipper.showNext()
//                    socketLightViewModel.updateRoom()
                }
            }
        })
        binding.changePinLayout.pinViewOtpCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString() // Get the OTP text from the PinView
                if(otp.length==4){
//                    utils.dismissKeyboard(requireView())

//                    socketLightViewModel.updateRoom()
                }
            }
        })
        binding.newPinLayout.pinViewOtpCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString() // Get the OTP text from the PinView
                if(otp.length==4){
                    utils.dismissKeyboard(requireView())
                    binding.changePinFlipper.showNext()

//                    socketLightViewModel.updateRoom()
                }
            }
        })
        binding.setUpConfirmPin.pinViewOtpCode.itemCount = 4
        binding.setUpConfirmPin.pinViewOtpCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString() // Get the OTP text from the PinView
                if(otp.length==4){
                    utils.dismissKeyboard(requireView())
                    binding.setUpFlipper.showNext()
                    Handler().postDelayed({
                        binding.setUpFlipper.showNext()
                    },1000)
                    Handler().postDelayed({
                        dismiss()
                    },1000)
//                    socketLightViewModel.updateRoom()
                }
            }
        })

    }

    private fun initChangePin(model: AddRoomModel) {

        binding.changePinLayout.tvTitle.text = "Enter New  4 digit PIN for living room security"
        binding.newPinLayout.tvTitle.text = "Enter Current  4 digit PIN for living room security"
        binding.changelayoutDone.tvDone.text = "Pin Change Successful"

    }

    private fun initSetUpLockPin(model: AddRoomModel) {
        binding.setUpPinLayout.tvHeader.text = "${model.model.text} Set Up"
        binding.setUpPinLayout.tvTitle.text = "Enter Lock Pin to Lock"
        binding.setUpPinLayout.tvSubTitle.text = model.model.text
        binding.setUpConfirmPin.tvHeader.text = "Confirm Pin"
        binding.setUpConfirmPin.tvTitle.text = "Confirm Lock Pin to Lock"
        binding.setUpConfirmPin.tvSubTitle.text = model.model.text
//        binding.layoutDone.tvDone.text = "Pin Set Up Complete"



    }

    private fun initForgotPassword(model: AddRoomModel) {
        binding.tvHeader.text = model.model.text + " " + resources.getString(R.string.lock_settings)
        binding.forgotPinRecovery.tvHeader.text = "UnLock ${model.model.text}"
        binding.forgotPinRecovery.tvTitle.text = "Pin has been sent to recovery phone number"
        binding.forgotPinRecovery.tvSubTitle.visibility = View.GONE
    }

    override fun observeData() {

    }

    override fun fetchData() {

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.btnForgotPin.id -> {
                binding.viewFlipper.displayedChild = 3
            }
            binding.btnSetUp.id -> {
                binding.viewFlipper.displayedChild = 2
            }
            binding.btnChangePin.id -> {
                binding.viewFlipper.displayedChild = 1
            }


            binding.btnSendOtp.id ->{
                binding.forgotFlipper.showNext()
            }
            binding.changePinLayout.btnContinue.id ->{
                binding.changePinFlipper.showNext()
            }
            binding.newPinLayout.btnContinue.id ->{
                dismiss()
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    Toast.makeText(context, "dismis", Toast.LENGTH_SHORT).show()
                    dismiss()
//                    binding.changePinFlipper.showNext()
                },1000)
            }


        }
    }


}