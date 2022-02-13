package com.example.firebaseauth

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

object CustomDialog {
    private var dialog: Dialog? = null

    fun showDialog(activity: Activity){
        val dialogViews = activity.layoutInflater.inflate(R.layout.layout_progress, null, false)

        dialog = Dialog(activity)
        dialog?.setCancelable(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setContentView(dialogViews)

        dialog?.show()
    }

    fun hideLoading(){
        dialog?.dismiss()
    }

}