package jafari.alireza.batman.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkUtil @Inject constructor(private val context: Context) {

    fun isConnectedToInternet(): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        return cm != null && cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected

    }
}

