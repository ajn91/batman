package jafari.alireza.batman.data.source.local.details


import jafari.alireza.batman.data.domain.derails.RatingModel

data class RatingEntity(
    val source: String,
    val value: String
)

fun List<RatingEntity>.asDomainModel(): List<RatingModel> {
    return map {
        RatingModel(
            source = it.source,
            value = it.value
        )
    }
}
