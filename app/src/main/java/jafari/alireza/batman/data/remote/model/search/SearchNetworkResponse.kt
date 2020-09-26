package jafari.alireza.batman.data.remote.model.search

import com.example.android.devbyteviewer.database.SearchEntity
import com.example.android.devbyteviewer.domain.SearchModel
import com.google.gson.annotations.SerializedName


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