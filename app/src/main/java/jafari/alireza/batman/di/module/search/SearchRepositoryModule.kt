package jafari.alireza.batman.di.module.search


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import jafari.alireza.batman.data.repository.search.SearchRepository
import jafari.alireza.batman.data.repository.search.SearchRepositoryImp


@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface SearchRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    fun bindSearchRepository(
        input: SearchRepositoryImp
    ): SearchRepository
}