package jafari.alireza.batman.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.batman.utils.NetworkUtil
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideUtils(@ApplicationContext context: Context): NetworkUtil = NetworkUtil(context)
}

