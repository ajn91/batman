package jafari.alireza.batman.di.component


import dagger.Component
import dagger.android.AndroidInjectionModule
import jafari.alireza.batman.AppController
import jafari.alireza.batman.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class,
        ApiModule::class, DbModule::class, ViewModelModule::class, RepositoryModule::class,
        ActivityModule::class]
)
interface ApiComponent {


    fun inject(appController: AppController)
}
