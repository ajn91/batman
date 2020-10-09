package jafari.alireza.batman.data.source.remote.details.pojo


import com.squareup.moshi.Json
import jafari.alireza.batman.data.domain.details.RatingModel
import jafari.alireza.batman.data.source.local.details.entity.RatingEntity

data class RatingNetwork(
    @field:Json(name = "Source")
    val source: String,
    @field:Json(name = "Value")
    val value: String
)


fun List<RatingNetwork>.asDomainModel(): List<RatingModel> {
    return map {
        RatingModel(
            source = it.source,
            value = it.value
        )
    }
}


fun List<RatingNetwork>.asDatabaseEntity(): List<RatingEntity> {
    return map {
        RatingEntity(
            source = it.source,
            value = it.value
        )
    }
}