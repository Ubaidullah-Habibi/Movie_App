package com.example.movieapp.view.base
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.movieapp.utils.InternetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    lateinit var mContext: Activity
    lateinit var fragmentActivity: FragmentActivity

    @Inject
    lateinit var mainDispatcher: MainCoroutineDispatcher
    @Inject
    lateinit var internetDialog: InternetDialog
    @Inject
    lateinit var coroutineScope: CoroutineScope

    private lateinit var handler: Handler

    fun initHandler(): Handler {
        if (!::handler.isInitialized) {
            return Handler(Looper.getMainLooper())
        }
        return handler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()
        fragmentActivity = requireActivity()
    }
}