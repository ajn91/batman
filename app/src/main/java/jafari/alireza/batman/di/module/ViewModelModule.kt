package jafari.alireza.batman.di.module


import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import jafari.alireza.batman.factory.ViewModelFactory


@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}