package jafari.alireza.batman.di.component


import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import jafari.alireza.batman.AppController
import jafari.alireza.batman.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class, ContextModule::class, DbModule::class,
        ViewModelModule::class, AndroidSupportInjectionModule::class,
        ActivityModule::class]
)
interface ApiComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModule: ApiModule): Builder

        @BindsInstance
        fun dbModule(dbModule: DbModule): Builder

        fun build(): ApiComponent
    }

    fun inject(appController: AppController)
}
