package jafari.alireza.batman.di.module


import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import jafari.alireza.batman.factory.ViewModelFactory


@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}