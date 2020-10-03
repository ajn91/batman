package jafari.alireza.batman.data.source.remote.pojo.search

import com.example.android.devbyteviewer.database.SearchEntity
import com.squareup.moshi.Json
import jafari.alireza.batman.data.domain.search.SearchModel


data class SearchNetworkResponse(

    @field:Json(name = "Search") val searchNetworks: List<SearchNetwork>,
    @field:Json(name = "totalResults") val totalResults: Int,
    @field:Json(name = "Response") val response: Boolean
)

fun SearchNetworkResponse.asDomainModel(): List<SearchModel> {
    return searchNetworks.map {
        SearchModel(
			imdbID = it.imdbID,
			title = it.title,
			year = it.year,
			type = it.type,
			poster = it.poster
		)


    }
}


/**
 * Convert Network results to database objects
 */
fun SearchNetworkResponse.asDatabaseEntity(): List<SearchEntity> {
    return searchNetworks.map {
        SearchEntity(
            imdbID = it.imdbID,
            title = it.title,
            year = it.year,
            type = it.type,
            poster = it.poster
        )
    }
}