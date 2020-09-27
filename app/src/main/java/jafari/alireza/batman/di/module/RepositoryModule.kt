package jafari.alireza.batman.di.module


import dagger.Binds
import dagger.Module
import jafari.alireza.batman.data.repository.details.DetailsRepository
import jafari.alireza.batman.data.repository.details.DetailsRepositoryImp
import jafari.alireza.batman.data.repository.search.SearchRepository
import jafari.alireza.batman.data.repository.search.SearchRepositoryImp


@Module
internal interface RepositoryModule {
    @Binds
    fun bindSearchRepository(
        input: SearchRepositoryImp
    ): SearchRepository

    @Binds
    fun bindDetailsRepository(
        input: DetailsRepositoryImp
    ): DetailsRepository
}