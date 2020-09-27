package jafari.alireza.batman.di.module


import dagger.Module
import dagger.android.ContributesAndroidInjector
import jafari.alireza.foursquare.ui.search.DetailsActivity
import jafari.alireza.foursquare.ui.search.SearchActivity


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeSearchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeDetailsActivity(): DetailsActivity
}