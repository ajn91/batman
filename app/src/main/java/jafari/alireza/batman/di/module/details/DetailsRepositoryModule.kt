package jafari.alireza.batman.di.module.details


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import jafari.alireza.batman.data.repository.details.DetailsRepository
import jafari.alireza.batman.data.repository.details.DetailsRepositoryImp


@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface DetailsRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    fun bindDetailsRepository(
        input: DetailsRepositoryImp
    ): DetailsRepository
}