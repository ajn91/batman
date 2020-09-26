package jafari.alireza.batman.di.module

import android.app.Application
import androidx.room.Room
import com.example.android.devbyteviewer.database.AppDatabase
import dagger.Module
import dagger.Provides
import jafari.alireza.batman.data.database.search.SearchDao
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {

        return Room.databaseBuilder(
            application, AppDatabase::class.java, provideDatabaseName()
        ).build()
    }

    @Provides
    @Singleton
    internal fun provideSearchDao(appDatabase: AppDatabase): SearchDao {
        return appDatabase.searchDao
    }

    internal fun provideDatabaseName(): String {
        return "batman.db"
    }

}
