package jafari.alireza.batman.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import jafari.alireza.batman.di.scope.ApplicationScope
import jafari.alireza.batman.utils.NetworkUtil

@Module
class AppModule {
//    @get:Provides
//    @Provides
//    @ApplicationScope
//    internal fun provideApplication(): Application {
//        return application
//    }

//    @Provides
//    @ApplicationScope
//    fun provideContext(appContext: Context): Context {
//        return appContext.applicationContext
//    }

    @Provides
    @ApplicationScope
    fun provideUtils(context: Context): NetworkUtil = NetworkUtil(context)
}

