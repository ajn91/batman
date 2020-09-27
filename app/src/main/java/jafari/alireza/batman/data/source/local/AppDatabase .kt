package com.example.android.devbyteviewer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.parax.panda.data.local.converter.RatingTypeConverter
import jafari.alireza.batman.data.source.local.details.DetailsDao
import jafari.alireza.batman.data.source.local.details.DetailsEntity
import jafari.alireza.batman.data.source.local.search.SearchDao


@Database(entities = [SearchEntity::class, DetailsEntity::class], version = 1, exportSchema = false)
@TypeConverters(RatingTypeConverter::class)

abstract class AppDatabase : RoomDatabase() {
    abstract val searchDao: SearchDao
    abstract val detailsDao: DetailsDao
}


