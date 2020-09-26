package jafari.alireza.batman.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jafari.alireza.batman.factory.ViewModelFactory
import jafari.alireza.foursquare.ui.search.SearchViewModel


@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    protected abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel

}