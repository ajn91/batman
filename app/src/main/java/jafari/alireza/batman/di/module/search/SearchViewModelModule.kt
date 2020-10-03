package jafari.alireza.batman.di.module.search

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jafari.alireza.batman.di.module.ViewModelKey
import jafari.alireza.batman.ui.search.SearchViewModel


@Module
interface SearchViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(input: SearchViewModel): ViewModel

//    companion object {
//        @Provides
//        @AddTodoScope
//        fun provideViewModelFactory(viewModelProviders: ViewModelProviders): ViewModelFactory {
//            return ViewModelFactory(viewModelProviders)
//        }
//    }
}