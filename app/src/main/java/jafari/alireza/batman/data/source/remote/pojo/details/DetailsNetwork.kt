package jafari.alireza.batman.data.source.remote.pojo.details


import com.squareup.moshi.Json
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.source.local.details.entity.DetailsEntity

data class DetailsNetwork(
    @field:Json(name = "Title")
    val title: String,
    @field:Json(name = "Year")
    val year: String,
    @field:Json(name = "Rated")
    val rated: String,
    @field:Json(name = "Released")
    val released: String,
    @field:Json(name = "Runtime")
    val runtime: String,
    @field:Json(name = "Genre")
    val genre: String,
    @field:Json(name = "Director")
    val director: String,
    @field:Json(name = "Writer")
    val writer: String,
    @field:Json(name = "Actors")
    val actors: String,
    @field:Json(name = "Plot")
    val plot: String,
    @field:Json(name = "Language")
    val language: String,
    @field:Json(name = "Country")
    val country: String,
    @field:Json(name = "Awards")
    val awards: String,
    @field:Json(name = "Poster")
    val poster: String,
    @field:Json(name = "Ratings")
    val rating: List<RatingNetwork>,
    @field:Json(name = "Metascore")
    val metascore: String,
    @field:Json(name = "imdbRating")
    val imdbRating: String,
    @field:Json(name = "imdbVotes")
    val imdbVotes: String,
    @field:Json(name = "imdbID")
    val imdbID: String,
    @field:Json(name = "Type")
    val type: String,
    @field:Json(name = "DVD")
    val dVD: String,
    @field:Json(name = "BoxOffice")
    val boxOffice: String,
    @field:Json(name = "Production")
    val production: String,
    @field:Json(name = "Website")
    val website: String,
    @field:Json(name = "Response")
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

