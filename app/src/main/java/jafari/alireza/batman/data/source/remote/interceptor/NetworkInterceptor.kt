package jafari.alireza.batman.data.source.remote.interceptor

import jafari.alireza.batman.utils.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor constructor(val networkUtil: NetworkUtil) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (networkUtil.isConnectedToInternet()) {
            request.newBuilder()
                .header("Cache-Control", "public, max-age=" + 60)
                .build()

        } else {
            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                .build()
        }

        return chain.proceed(request)
    }
}
