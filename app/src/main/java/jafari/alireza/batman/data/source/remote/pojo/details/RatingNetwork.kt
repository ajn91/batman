package jafari.alireza.batman.data.source.remote.pojo.details


import com.google.gson.annotations.SerializedName
import jafari.alireza.batman.data.domain.details.RatingModel
import jafari.alireza.batman.data.source.local.details.entity.RatingEntity

data class RatingNetwork(
    @SerializedName("Source")
    val source: String,
    @SerializedName("Value")
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