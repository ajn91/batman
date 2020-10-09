package jafari.alireza.batman.data.source.remote.jsonadapters

import com.squareup.moshi.*


@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class StringToBoolean

object StringToBooleanAdapter : JsonAdapter<Boolean>() {
    @FromJson
    @StringToBoolean
    override fun fromJson(reader: JsonReader): Boolean {
        return when (reader.peek()) {
            JsonReader.Token.NUMBER -> reader.nextInt() != 0
            JsonReader.Token.BOOLEAN -> reader.nextBoolean()
            JsonReader.Token.STRING -> reader.nextString().toBoolean()
            else -> {
                reader.skipValue() // or throw
                false
            }
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, @StringToBoolean value: Boolean?) {
        writer.value(value)
    }
}


@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class StringToFloat

object StringToFloatAdapter : JsonAdapter<Float>() {
    @FromJson
    @StringToFloat
    override fun fromJson(reader: JsonReader): Float {
        return when (reader.peek()) {
            JsonReader.Token.NUMBER -> reader.nextDouble().toFloat()
            JsonReader.Token.STRING -> reader.nextString().toFloat()
            else -> {
                reader.skipValue() // or throw
                0f
            }
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, @StringToBoolean value: Float?) {
        writer.value(value)
    }
}