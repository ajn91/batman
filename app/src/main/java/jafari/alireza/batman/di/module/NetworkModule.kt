package jafari.alireza.batman.di.module

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.batman.data.source.remote.api.ApiService
import jafari.alireza.batman.data.source.remote.interceptor.NetworkInterceptor
import jafari.alireza.batman.data.source.remote.interceptor.RequestInterceptor
import jafari.alireza.batman.data.source.remote.jsonadapters.StringToBooleanAdapter
import jafari.alireza.batman.data.source.remote.jsonadapters.StringToFloatAdapter
import jafari.alireza.batman.utils.NetworkUtil
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    //    @Provides
//    @Singleton
//    internal fun provideGson() =
//        GsonBuilder().create()
    @Provides
    @Singleton
    internal fun provideMoshi() =
        Moshi.Builder().add(StringToBooleanAdapter).add(StringToFloatAdapter).build()


    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }


    @Provides
    @Singleton
    fun provideNetworkInterceptor(networkUtil: NetworkUtil) =
        NetworkInterceptor(networkUtil)

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideRequestInterceptor() =
        RequestInterceptor()


    @Provides
    @Singleton
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
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @Named("serverUrl") serverUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(serverUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) =
        retrofit.create(ApiService::class.java)

    @Provides
    @Named("serverUrl")
    fun provideServerUrl() = "http://www.omdbapi.com/"


}
