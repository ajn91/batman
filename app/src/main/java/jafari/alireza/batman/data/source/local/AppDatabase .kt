package com.example.android.devbyteviewer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import jafari.alireza.batman.data.source.local.search.SearchDao


@Database(entities = [SearchEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val searchDao: SearchDao
}


