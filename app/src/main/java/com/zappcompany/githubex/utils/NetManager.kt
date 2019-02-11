package com.zappcompany.githubex.utils

import android.content.Context
import android.net.ConnectivityManager

class NetManager(private val context: Context) {
    private var status: Boolean? = false

    val isConnectedToInternet: Boolean?
        get() {
            val conManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}