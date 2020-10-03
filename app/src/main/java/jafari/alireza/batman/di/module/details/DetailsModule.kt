package jafari.alireza.batman.di.module.details

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jafari.alireza.batman.ui.details.DetailsActivity


@Module
public interface DetailsModule {

    @DetailsScope
    @ContributesAndroidInjector(modules = [DetailsViewModelModule::class, DetailsRepositoryModule::class])
    fun bindDetailsActivity(): DetailsActivity
}
