package com.example.android.devbyteviewer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import jafari.alireza.batman.data.domain.search.SearchModel


/**
 * Database entities go in this file. These are responsible for reading and writing from the
 * database.
 */


/**
 * DatabaseSearch  represents a search entity in the database.
 */
@Entity(tableName = "searches")
data class SearchEntity constructor(
    @PrimaryKey
    val imdbID: String,
    val title: String,
    val year: String,
    val type: String,
    val poster: String
)

/**
 * Map DatabaseSearch  to domain entities
 */
fun List<SearchEntity>.asDomainModel(): List<SearchModel> {
    return map {
        SearchModel(
            imdbID = it.imdbID,
            title = it.title,
            year = it.year,
            type = it.type,
            poster = it.poster
        )
    }
}

