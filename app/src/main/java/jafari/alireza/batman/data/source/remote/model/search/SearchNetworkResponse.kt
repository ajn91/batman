package jafari.alireza.batman.data.source.remote.model.search

import com.example.android.devbyteviewer.database.SearchEntity
import com.google.gson.annotations.SerializedName
import jafari.alireza.batman.data.domain.SearchModel


data class SearchNetworkResponse(

	@SerializedName("Search") val searchNetworks: List<SearchNetwork>,
	@SerializedName("totalResults") val totalResults: Int,
	@SerializedName("Response") val response: Boolean
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
fun SearchNetworkResponse.asDatabaseModel(): List<SearchEntity> {
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