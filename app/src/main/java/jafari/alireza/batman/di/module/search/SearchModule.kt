package jafari.alireza.batman.di.module.search

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jafari.alireza.batman.ui.search.SearchActivity


@Module
abstract class SearchModule {

    @SearchScope
    @ContributesAndroidInjector(modules = [SearchViewModelModule::class, SearchRepositoryModule::class])
    abstract fun bindSearchActivity(): SearchActivity
}
