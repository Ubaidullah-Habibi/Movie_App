package com.example.movieapp.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movieapp.R
import com.example.movieapp.utils.setOnSafeClickListener
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.databinding.ExitDialogLayoutBinding
import com.example.movieapp.view.base.BaseActivity
import com.example.movieapp.view.search.SearchActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.searchBar.setOnSafeClickListener {
            val searchIntent = Intent(mContext, SearchActivity::class.java)
            startActivity(searchIntent)
        }
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer, SearchFragment())
//                .commit()
//        }
    }

    private fun displayExitDialog(){
        val exitDialog = BottomSheetDialog(mContext)
        val exitBinding = ExitDialogLayoutBinding.inflate(layoutInflater)
        exitDialog.setContentView(exitBinding.root)
        with(exitBinding){
            tvAlertDialogTitle.text = getString(R.string.exit)
            tvAlertDialogMessage.text = getString(R.string.are_you_sure_you_want_to_exit_the_app)
            btnPositive.text = getString(R.string.exit)
            btnNegative.text = getString(R.string.cancel)
            btnPositive.setOnClickListener{
                    finish()
            }
            btnNegative.setOnClickListener{
                exitDialog.dismiss()
            }
        }
        exitDialog.setCanceledOnTouchOutside(false)
        exitDialog.show()
    }
    override fun handleBackPressed() {
        displayExitDialog()
    }
}