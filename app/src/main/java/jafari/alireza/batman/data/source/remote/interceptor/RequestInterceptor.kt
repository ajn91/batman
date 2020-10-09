package jafari.alireza.batman.data.source.remote.interceptor


import jafari.alireza.batman.OMDB_API_KEY
import jafari.alireza.batman.OMDB_API_KEY_NAME
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder()
            .addQueryParameter(OMDB_API_KEY_NAME, OMDB_API_KEY)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
