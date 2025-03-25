package com.example.movieapp.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build



class InternetController (private val connectivityManager: ConnectivityManager) {
    val isInternetConnected: Boolean
        get() {
            try {
                val network = connectivityManager.activeNetwork
                if (network != null) {
                    val nc = connectivityManager.getNetworkCapabilities(network)
                    if (nc != null && (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                nc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                    ) {
                        return true
                    }
                }
            } catch (_: Exception) {
            }
            return false
        }
}