package jafari.alireza.batman.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import jafari.alireza.batman.GENERAL_LOG_TAG
import kotlinx.coroutines.Dispatchers


fun <T, A> performGetOperation(
    errorMessage: String = "No item available2",
    localFetch: (() -> LiveData<T?>)? = null,
    remoteFetch: (suspend () -> Resource<A?>)? = null,
    saveRemoteResult: (suspend (A) -> Unit)? = null,
    mapRemoteToModel: (suspend (A?) -> T?)? = null

): LiveData<Resource<T?>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        if (remoteFetch == null && localFetch == null) {
            emit(Resource.error(errorMessage))
            return@liveData
        }

        if (localFetch != null) {
            try {
                val source = localFetch.invoke().map {
                    if (it != null)
                        Resource.success(it)
                    else
                        if (remoteFetch == null)
                            Resource.error<T?>(errorMessage)
                        else
                            Resource.loading()

                }
                emitSource(source)
            } catch (e: Exception) {
                Log.d(GENERAL_LOG_TAG, "performGetOperation: ${e.message}")
                emit(Resource.error<T?>(errorMessage))

            }
        }

        if (remoteFetch != null) {
            try {
                val responseStatus = remoteFetch.invoke()
                if (responseStatus.status == Status.SUCCESS) {
                    if (saveRemoteResult != null)
                        saveRemoteResult(responseStatus.data!!)
                    else {
                        val data = mapRemoteToModel!!.invoke(responseStatus.data)
                        emit(Resource.success(data))
                    }

                } else if (responseStatus.status == Status.ERROR)
                    emit(Resource.error(responseStatus.message!!))

            } catch (e: Exception) {
                Log.d(GENERAL_LOG_TAG, "performGetOperation: ${e.message}")
                emit(Resource.error<T?>(errorMessage))

            }
        }

    }