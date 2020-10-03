package jafari.alireza.batman.di.module.details


import dagger.Binds
import dagger.Module
import jafari.alireza.batman.data.repository.details.DetailsRepository
import jafari.alireza.batman.data.repository.details.DetailsRepositoryImp


@Module
internal interface DetailsRepositoryModule {

    @DetailsScope
    @Binds
    fun bindDetailsRepository(
        input: DetailsRepositoryImp
    ): DetailsRepository
}