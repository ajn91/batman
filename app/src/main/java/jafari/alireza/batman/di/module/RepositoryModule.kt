package jafari.alireza.batman.di.module


import dagger.Binds
import dagger.Module
import jafari.alireza.batman.data.repository.SearchRepository
import jafari.alireza.batman.data.repository.SearchRepositoryImp


@Module
internal interface RepositoryModule {
    @Binds
    fun bindSearchRepository(
        input: SearchRepositoryImp
    ): SearchRepository
}