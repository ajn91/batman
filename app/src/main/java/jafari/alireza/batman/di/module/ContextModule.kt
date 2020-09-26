package jafari.alireza.batman.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class ContextModule {
    @Provides
    @Inject
    fun provideContext(application: Application): Context {
        return application
    }
}