package jafari.alireza.batman.data.source.local.details


import androidx.room.Entity
import androidx.room.PrimaryKey
import jafari.alireza.batman.data.domain.derails.DetailsModel


@Entity
data class DetailsEntity(
    @PrimaryKey
    val imdbID: String,
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val poster: String,
    val rating: List<RatingEntity>,
    val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val type: String,
    val dVD: String,
    val boxOffice: String,
    val production: String,
    val website: String
)


fun DetailsEntity.asDomainModel(): DetailsModel {
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

