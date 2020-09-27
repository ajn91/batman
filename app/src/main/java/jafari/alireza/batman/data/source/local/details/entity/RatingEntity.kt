package jafari.alireza.batman.data.source.local.details.entity


import jafari.alireza.batman.data.domain.details.RatingModel

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
