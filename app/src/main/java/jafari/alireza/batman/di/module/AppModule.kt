package jafari.alireza.batman.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import jafari.alireza.batman.utils.NetworkUtil

import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideUtils(): NetworkUtil = NetworkUtil(application)
}

