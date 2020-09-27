package jafari.alireza.batman.data.source.remote.pojo.search

import com.google.gson.annotations.SerializedName



data class SearchNetwork(

    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String
)