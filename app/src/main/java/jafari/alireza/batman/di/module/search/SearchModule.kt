package jafari.alireza.batman.di.module.search


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import jafari.alireza.batman.data.repository.search.SearchRepository
import jafari.alireza.batman.data.repository.search.SearchRepositoryImp
import jafari.alireza.batman.data.source.local.search.SearchLocalDataSource
import jafari.alireza.batman.data.source.local.search.SearchLocalDataSourceImp
import jafari.alireza.batman.data.source.remote.search.SearchRemoteDataSource
import jafari.alireza.batman.data.source.remote.search.SearchRemoteRemoteDataSourceImp


@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface SearchModule {

    @ActivityRetainedScoped
    @Binds
    fun bindSearchRepository(
        input: SearchRepositoryImp
    ): SearchRepository

    @ActivityRetainedScoped
    @Binds
    fun bindSearchRemoteDataSource(
        input: SearchRemoteRemoteDataSourceImp
    ): SearchRemoteDataSource

    @ActivityRetainedScoped
    @Binds
    fun bindSearchLocalDataSource(
        input: SearchLocalDataSourceImp
    ): SearchLocalDataSource
}