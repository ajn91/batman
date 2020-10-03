package jafari.alireza.batman.data.source.local.details.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import jafari.alireza.batman.data.source.local.details.entity.RatingEntity


class RatingTypeConverter() {

    @TypeConverter
    fun fromString(value: String): List<RatingEntity>? {

        return getRatingListTypeAdapter().fromJson(value)

//
//        val listType = object : TypeToken<List<RatingEntity>>() {}.type
//        return Gson().fromJson<List<RatingEntity>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<RatingEntity>?): String {

        return getRatingListTypeAdapter().toJson(list)
    }


    fun getRatingListTypeAdapter(): JsonAdapter<List<RatingEntity>> {
        val type = Types.newParameterizedType(
            List::class.java,
            RatingEntity::class.java
        )
        return Moshi.Builder().build().adapter(type)
    }

}
