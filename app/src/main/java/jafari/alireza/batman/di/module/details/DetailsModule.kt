package jafari.alireza.batman.di.module.details


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import jafari.alireza.batman.data.repository.details.DetailsRepository
import jafari.alireza.batman.data.repository.details.DetailsRepositoryImp
import jafari.alireza.batman.data.source.local.details.DetailsLocalDataSource
import jafari.alireza.batman.data.source.local.details.DetailsLocalDataSourceImp
import jafari.alireza.batman.data.source.remote.details.DetailsRemoteDataSource
import jafari.alireza.batman.data.source.remote.details.DetailsRemoteRemoteDataSourceImp


@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface DetailsModule {

    @ActivityRetainedScoped
    @Binds
    fun bindDetailsRepository(
        input: DetailsRepositoryImp
    ): DetailsRepository

    @ActivityRetainedScoped
    @Binds
    fun bindDetailsRemoteDataSource(
        input: DetailsRemoteRemoteDataSourceImp
    ): DetailsRemoteDataSource

    @ActivityRetainedScoped
    @Binds
    fun bindDetailsLocalDataSource(
        input: DetailsLocalDataSourceImp
    ): DetailsLocalDataSource

}