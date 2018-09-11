package com.abraham.searchmoviemvvm.ui.main.repository.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class MyApplication: Application(){
    companion object {
        fun get(context: Context?): MyApplication {
            return context?.applicationContext as MyApplication
        }
    }

    fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}