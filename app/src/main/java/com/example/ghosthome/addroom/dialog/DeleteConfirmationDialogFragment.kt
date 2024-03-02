package com.example.ghosthome.addroom.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.ghosthome.addroom.viewmodel.AddRoomViewModel
import com.example.ghosthome.databinding.FragmentDeleteConfirmationDialogBinding


class DeleteConfirmationDialogFragment : DialogFragment() {
    lateinit var binding:FragmentDeleteConfirmationDialogBinding
    private val sharedViewModel: AddRoomViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeleteConfirmationDialogBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        onClickListener()
        return binding.root
    }
    private fun onClickListener() {
        binding.btnNo.setOnClickListener {
            dismiss()
        }
        binding.btnYes.setOnClickListener {
            binding.firstLayout.visibility=View.GONE
            binding.secondLayout.visibility=View.VISIBLE
//            dismiss()
        }
        binding.btnContinue.setOnClickListener {

            dismiss()
        }
        binding.pinViewOtpCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString() // Get the OTP text from the PinView
                if(otp.length==6){
                    binding.secondLayout.visibility=View.GONE
                    binding.layoutLoading.visibility=View.VISIBLE
                    Handler().postDelayed({
                        sharedViewModel.deleteRoom(0)
                        binding.layoutLoading.visibility=View.GONE
                        binding.continueLayout.visibility=View.VISIBLE

                    },
                        1000)
                }
            }
        })
    }


}