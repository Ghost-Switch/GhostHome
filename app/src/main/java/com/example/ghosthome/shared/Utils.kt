package com.example.ghosthome.shared

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar

class Utils {

     fun showSnackBar(msg:String,view:View) {
        val snack: Snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
        val view = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP or Gravity.CENTER
        view.layoutParams = params
        snack.show()
    }
}