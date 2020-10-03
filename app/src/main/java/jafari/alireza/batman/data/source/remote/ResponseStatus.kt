package jafari.alireza.batman.data.source.remote


sealed class ResponseStatus {

    class SUCCESS() : ResponseStatus()
    class ERROR(val message: String) : ResponseStatus()
    class LOADING : ResponseStatus()

}