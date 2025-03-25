package com.example.movieapp.utils

import android.view.View
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InputController @Inject constructor(private val inputMethodManager: InputMethodManager) {
    fun showKeyboard(view:View){
        try {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
            view.requestFocus()
        } catch (e: Exception) {
        }
    }
    fun hideKeyboard(view: View){
        try {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
        } catch (e: Exception) {
        }
    }
}