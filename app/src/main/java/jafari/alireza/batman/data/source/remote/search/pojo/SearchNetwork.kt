package jafari.alireza.batman.data.source.remote.search.pojo

import com.squareup.moshi.Json


data class SearchNetwork(

    @field:Json(name = "Title") val title: String?,
    @field:Json(name = "Year") val year: String?,
    @field:Json(name = "imdbID") val imdbID: String?,
    @field:Json(name = "Type") val type: String?,
    @field:Json(name = "Poster") val poster: String?,
)