package jafari.alireza.batman.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import jafari.alireza.batman.data.source.remote.api.ApiService
import jafari.alireza.batman.data.source.remote.interceptor.NetworkInterceptor
import jafari.alireza.batman.data.source.remote.interceptor.RequestInterceptor
import jafari.alireza.batman.di.scope.ApplicationScope
import jafari.alireza.batman.utils.NetworkUtil
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named


@Module
class NetworkModule {

//    @Provides
//    @Singleton
//    internal fun provideGson() =
//        GsonBuilder().create()
//    @Provides
//    @Singleton
//    internal fun provideMoshi() =
//    Moshi.Builder().build()


    @Provides
    @ApplicationScope
    fun provideCache(context: Context): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }


    @Provides
    @ApplicationScope
    fun provideNetworkInterceptor(networkUtil: NetworkUtil) =
        NetworkInterceptor(networkUtil)

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor()

    @Provides
    @ApplicationScope
    fun provideRequestInterceptor() =
        RequestInterceptor()


    @Provides
    @ApplicationScope
    fun provideOkhttpClient(
        cache: Cache,
        networkInterceptor: NetworkInterceptor,
        requestInterceptor: RequestInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(requestInterceptor)
        httpClient.addInterceptor(httpLoggingInterceptor)
        httpClient.addNetworkInterceptor(networkInterceptor)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named("serverUrl") serverUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(serverUrl)
//            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    @Provides
    @ApplicationScope
    fun provideApiService(retrofit: Retrofit) =
        retrofit.create(ApiService::class.java)

    @Provides
    @Named("serverUrl")
    fun provideServerUrl() = "http://www.omdbapi.com/"


}