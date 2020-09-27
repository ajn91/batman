package jafari.alireza.batman.data.source.remote


class Resource<T> private constructor(
    val status: ResourceStatus,
    val data: T?,
    val message: String?
) {


    val isSuccess: Boolean
        get() = status == ResourceStatus.SUCCESS && data != null

    val isError: Boolean
        get() = status == ResourceStatus.ERROR

    val isLoading: Boolean
        get() = status == ResourceStatus.LOADING

    val isLoaded: Boolean
        get() = status == ResourceStatus.LOADED

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(ResourceStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ResourceStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(ResourceStatus.LOADING, data, null)
        }
    }
}