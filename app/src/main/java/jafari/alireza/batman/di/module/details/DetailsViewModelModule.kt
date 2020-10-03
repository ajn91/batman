package jafari.alireza.batman.di.module.details

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jafari.alireza.batman.di.module.ViewModelKey
import jafari.alireza.batman.ui.details.DetailsViewModel

@Module
interface DetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun detailsViewModel(input: DetailsViewModel): ViewModel

//    companion object {
//        @Provides
//        @AddTodoScope
//        fun provideViewModelFactory(viewModelProviders: ViewModelProviders): ViewModelFactory {
//            return ViewModelFactory(viewModelProviders)
//        }
//    }
}