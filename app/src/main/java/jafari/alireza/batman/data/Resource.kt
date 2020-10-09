package jafari.alireza.batman.data

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    public val isSuccess: Boolean
        get() = status == Status.SUCCESS

    val isError: Boolean
        get() = status == Status.ERROR

    val isLoading: Boolean
        get() = status == Status.LOADING


    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}