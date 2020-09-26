package jafari.alireza.batman.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import jafari.alireza.batman.utils.NetworkUtil
import javax.inject.Inject

import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @Inject
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideUtils(application: Application): NetworkUtil = NetworkUtil(application)
}

