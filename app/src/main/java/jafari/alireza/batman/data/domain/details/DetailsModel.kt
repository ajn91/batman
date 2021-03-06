package jafari.alireza.batman.data.domain.details


data class DetailsModel(
    val imdbID: String?,
    val title: String?,
    val year: String?,
    val rated: String?,
    val released: String?,
    val runtime: String?,
    val genre: String?,
    val director: String?,
    val writer: String?,
    val actors: String?,
    val plot: String?,
    val language: String?,
    val country: String?,
    val awards: String?,
    val poster: String?,
    val rating: List<RatingModel>?,
    val metascore: String?,
    val imdbRating: Float,
    val imdbVotes: String?,
    val type: String?,
    val dVD: String?,
    val boxOffice: String?,
    val production: String?,
    val website: String?
)