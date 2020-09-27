package jafari.alireza.batman.data.source.remote.pojo.details


import com.google.gson.annotations.SerializedName
import jafari.alireza.batman.data.domain.derails.DetailsModel
import jafari.alireza.batman.data.source.local.details.DetailsEntity

data class DetailsNetwork(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Rated")
    val rated: String,
    @SerializedName("Released")
    val released: String,
    @SerializedName("Runtime")
    val runtime: String,
    @SerializedName("Genre")
    val genre: String,
    @SerializedName("Director")
    val director: String,
    @SerializedName("Writer")
    val writer: String,
    @SerializedName("Actors")
    val actors: String,
    @SerializedName("Plot")
    val plot: String,
    @SerializedName("Language")
    val language: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Awards")
    val awards: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Ratings")
    val rating: List<RatingNetwork>,
    @SerializedName("Metascore")
    val metascore: String,
    @SerializedName("imdbRating")
    val imdbRating: String,
    @SerializedName("imdbVotes")
    val imdbVotes: String,
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("DVD")
    val dVD: String,
    @SerializedName("BoxOffice")
    val boxOffice: String,
    @SerializedName("Production")
    val production: String,
    @SerializedName("Website")
    val website: String,
    @SerializedName("Response")
    val response: String
)

fun DetailsNetwork.asDomainModel(): DetailsModel {
    return DetailsModel(
        imdbID = this.imdbID,
        title = this.title,
        year = this.year,
        rated = this.rated,
        released = this.released,
        runtime = this.runtime,
        genre = this.genre,
        director = this.director,
        writer = this.writer,
        actors = this.actors,
        plot = this.plot,
        language = this.language,
        country = this.country,
        awards = this.awards,
        poster = this.poster,
        rating = this.rating.asDomainModel(),
        metascore = this.metascore,
        imdbRating = this.imdbRating,
        imdbVotes = this.imdbVotes,
        type = this.type,
        dVD = this.dVD,
        boxOffice = this.boxOffice,
        production = this.production,
        website = this.website

    )
}


/**
 * Convert Network results to database objects
 */

fun DetailsNetwork.asDatabaseEntity(): DetailsEntity {
    return DetailsEntity(
        imdbID = this.imdbID,
        title = this.title,
        year = this.year,
        rated = this.rated,
        released = this.released,
        runtime = this.runtime,
        genre = this.genre,
        director = this.director,
        writer = this.writer,
        actors = this.actors,
        plot = this.plot,
        language = this.language,
        country = this.country,
        awards = this.awards,
        poster = this.poster,
        rating = this.rating.asDatabaseEntity(),
        metascore = this.metascore,
        imdbRating = this.imdbRating,
        imdbVotes = this.imdbVotes,
        type = this.type,
        dVD = this.dVD,
        boxOffice = this.boxOffice,
        production = this.production,
        website = this.website

    )
}