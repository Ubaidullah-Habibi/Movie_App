package com.example.movieapp.utils

class CallBacks {
    interface SimpleAlertDialog {
        fun positiveButtonClick(text: String = "")
        fun negativeButtonClick()
    }
}