package com.example.android.devbyteviewer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jafari.alireza.batman.data.source.local.details.DetailsDao
import jafari.alireza.batman.data.source.local.details.converter.RatingTypeConverter
import jafari.alireza.batman.data.source.local.details.entity.DetailsEntity
import jafari.alireza.batman.data.source.local.search.SearchDao


@Database(entities = [SearchEntity::class, DetailsEntity::class], version = 1, exportSchema = false)
@TypeConverters(RatingTypeConverter::class)

abstract class AppDatabase : RoomDatabase() {
    abstract val searchDao: SearchDao
    abstract val detailsDao: DetailsDao
}


