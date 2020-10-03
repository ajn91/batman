package jafari.alireza.batman.di.module

import android.content.Context
import androidx.room.Room
import com.example.android.devbyteviewer.database.AppDatabase
import dagger.Module
import dagger.Provides
import jafari.alireza.batman.data.source.local.details.DetailsDao
import jafari.alireza.batman.data.source.local.search.SearchDao
import jafari.alireza.batman.di.scope.ApplicationScope
import javax.inject.Named

@Module
class DbModule {

    @Provides
    @ApplicationScope
    internal fun provideDatabase(context: Context, @Named("dbName") dbName: String): AppDatabase {

        return Room.databaseBuilder(
            context, AppDatabase::class.java, dbName
        ).build()
    }

    @Provides
    @ApplicationScope
    internal fun provideSearchDao(appDatabase: AppDatabase): SearchDao {
        return appDatabase.searchDao
    }

    @Provides
    @ApplicationScope
    internal fun provideDetailsDao(appDatabase: AppDatabase): DetailsDao {
        return appDatabase.detailsDao
    }

    @Provides
    @ApplicationScope
    @Named("dbName")
    fun provideDatabaseName() = "batman.db"


}
