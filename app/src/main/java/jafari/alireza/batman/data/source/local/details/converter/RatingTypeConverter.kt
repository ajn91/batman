package com.parax.panda.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jafari.alireza.batman.data.source.local.details.entity.RatingEntity

class RatingTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<RatingEntity>? {
        val listType = object : TypeToken<List<RatingEntity>>() {}.type
        return Gson().fromJson<List<RatingEntity>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<RatingEntity>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
