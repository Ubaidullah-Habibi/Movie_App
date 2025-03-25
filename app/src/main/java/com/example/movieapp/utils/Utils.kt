package com.example.movieapp.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

object Utils {
    var isInternetDialogShowing = false
    fun updateStatusBarColor(context: Activity, color: Int) {
        val window: Window = context.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = color
    }
}