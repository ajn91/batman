package jafari.alireza.batman.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkUtil @Inject constructor(private val application: Application) {

    fun isConnectedToInternet(): Boolean {
        val cm =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        return cm != null && cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected

    }
}

