package jafari.alireza.batman.di.module


import dagger.Module
import dagger.android.ContributesAndroidInjector
import jafari.alireza.foursquare.ui.search.SearchActivity


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeSearchActivity(): SearchActivity
}