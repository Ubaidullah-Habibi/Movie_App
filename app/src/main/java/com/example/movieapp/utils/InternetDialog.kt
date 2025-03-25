package com.example.movieapp.utils
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.widget.Toast
import com.example.movieapp.databinding.InternetDialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InternetDialog @Inject constructor() {
    //    private var alertDialog: AlertDialog? = null
    private var alertDialog: BottomSheetDialog? = null

    fun showConnectToWifiDialog(context: Activity) {
        alertDialog = BottomSheetDialog(context)
        val binding = InternetDialogLayoutBinding.inflate(context.layoutInflater)
        alertDialog!!.setContentView(binding.root)
        binding.btnConnectNow.setOnClickListener {
            Utils.isInternetDialogShowing = false
            openInternetSettings(context)
            hideAlertDialog(context, alertDialog!!)

        }
        binding.imgCross.setOnClickListener {
            Utils.isInternetDialogShowing = false
            hideAlertDialog(context, alertDialog!!)

        }
        alertDialog!!.setCanceledOnTouchOutside(false)
        showDialogue(context, alertDialog!!)
    }

    private fun openInternetSettings(context: Context) {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        if (intent.resolveActivity(context.packageManager) != null) {
            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    context,
                    "Failed to open Wifi Settings", Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                context,
                "Wifi Settings not available", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showDialogue(
        mContext: Activity, alertDialog: Dialog
    ) {
        if (!mContext.isFinishing && !mContext.isDestroyed && !alertDialog.isShowing) {
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
        }
    }

    private fun hideAlertDialog(activity: Activity, dialog: AlertDialog?) {
        dialog?.let {
            try {
                if (it.isShowing && !activity.isFinishing && !activity.isDestroyed) {
                    it.dismiss()
                }
            } catch (_: Exception) {
            }
        }
    }

    private fun hideAlertDialog(activity: Activity, dialog: BottomSheetDialog?) {
        dialog?.let {
            try {
                if (it.isShowing && !activity.isFinishing && !activity.isDestroyed) {
                    it.dismiss()
                }
            } catch (_: Exception) {
            }
        }
    }
}