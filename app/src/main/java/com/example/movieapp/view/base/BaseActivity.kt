package com.example.movieapp.view.base
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.movieapp.R
import com.example.movieapp.utils.InputController
import com.example.movieapp.utils.InternetDialog
import com.example.movieapp.utils.Utils
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: AppCompatActivity

    @Inject
     lateinit var internetDialog: InternetDialog

     @Inject
     lateinit var inputController: InputController

     private lateinit var handler: Handler

    fun initHandler(): Handler {
        if (!::handler.isInitialized) {
            return Handler(Looper.getMainLooper())
        }
        return handler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        val statusBarColor = ContextCompat.getColor(mContext, R.color.bg_color)
        Utils.updateStatusBarColor(mContext, statusBarColor!!)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackPressed()
            }
        })
    }

    abstract fun handleBackPressed()
}