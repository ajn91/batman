package jafari.alireza.batman.di.component


import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import jafari.alireza.batman.AppController
import jafari.alireza.batman.di.module.AppModule
import jafari.alireza.batman.di.module.DbModule
import jafari.alireza.batman.di.module.NetworkModule
import jafari.alireza.batman.di.module.ViewModelFactoryModule
import jafari.alireza.batman.di.module.details.DetailsModule
import jafari.alireza.batman.di.module.search.SearchModule
import jafari.alireza.batman.di.scope.ApplicationScope

@ApplicationScope
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class,
        NetworkModule::class, DbModule::class, ViewModelFactoryModule::class, DetailsModule::class, SearchModule::class]
)
interface AppComponent : AndroidInjector<AppController> {
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

}
