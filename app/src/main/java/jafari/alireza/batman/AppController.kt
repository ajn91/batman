package jafari.alireza.batman

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import jafari.alireza.batman.di.component.DaggerApiComponent
import jafari.alireza.batman.di.module.ApiModule
import jafari.alireza.batman.di.module.AppModule
import jafari.alireza.batman.di.module.DbModule
import javax.inject.Inject

class AppController : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerApiComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule())
            .dbModule(DbModule())
            .build()
            .inject(this)

    }


}
