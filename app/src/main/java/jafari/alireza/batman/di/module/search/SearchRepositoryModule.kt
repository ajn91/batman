package jafari.alireza.batman.di.module.search


import dagger.Binds
import dagger.Module
import jafari.alireza.batman.data.repository.search.SearchRepository
import jafari.alireza.batman.data.repository.search.SearchRepositoryImp


@Module
internal interface SearchRepositoryModule {

    @SearchScope
    @Binds
    fun bindSearchRepository(
        input: SearchRepositoryImp
    ): SearchRepository
}