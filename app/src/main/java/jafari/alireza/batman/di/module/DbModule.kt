package jafari.alireza.batman.di.module

import android.content.Context
import androidx.room.Room
import com.example.android.devbyteviewer.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.batman.data.source.local.details.DetailsDao
import jafari.alireza.batman.data.source.local.search.SearchDao
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DbModule {

    @Provides
    @Singleton
    internal fun provideDatabase(
        @ApplicationContext context: Context,
        @Named("dbName") dbName: String
    ): AppDatabase {

        return Room.databaseBuilder(
            context, AppDatabase::class.java, dbName
        ).build()
    }

    @Provides
    @Singleton
    internal fun provideSearchDao(appDatabase: AppDatabase): SearchDao {
        return appDatabase.searchDao
    }

    @Provides
    @Singleton
    internal fun provideDetailsDao(appDatabase: AppDatabase): DetailsDao {
        return appDatabase.detailsDao
    }

    @Provides
    @Singleton
    @Named("dbName")
    fun provideDatabaseName() = "batman.db"


}
