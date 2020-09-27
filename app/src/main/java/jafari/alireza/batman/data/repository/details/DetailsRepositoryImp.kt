package jafari.alireza.batman.data.repository.details

import android.content.Context
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.R
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.source.local.details.DetailsDao
import jafari.alireza.batman.data.source.local.details.entity.asDomainModel
import jafari.alireza.batman.data.source.remote.Resource
import jafari.alireza.batman.data.source.remote.pojo.details.asDatabaseEntity
import jafari.alireza.batman.utils.NetworkUtil
import jafari.alireza.foursquare.data.remote.api.ApiService
import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    val apiService: ApiService,
    val detailsDao: DetailsDao,
    val networkUtil: NetworkUtil,
    val context: Context
) : DetailsRepository {

    override fun getDetails(id: String): Flowable<Resource<out DetailsModel>> {
        val hasConnection = networkUtil.isConnectedToInternet()
        if (hasConnection)
            getDetailsFromApi(id)

        return getDetailsFromDb(id).map {
            if (it.size == 0) {
                if (hasConnection)
                    Resource.error(context.getString(R.string.empty_data), null)
                else
                    Resource.error(context.getString(R.string.empty_data_no_network), null)
            } else
                Resource.success(it[0])
        }.startWith(Resource.loading(null))
            .onErrorReturn {
                Resource.error(it.message ?: "error", null)
            }


    }

    fun getDetailsFromApi(id: String) =
        apiService.getDetails(id).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ response ->
                Log.d("LOG", "getSearchFromApi: ")

                detailsDao.insertDetailsItem(response.asDatabaseEntity())

            }, { error ->
//        messageStringLive.value = error.message
            })

    fun getDetailsFromDb(id: String): Flowable<List<DetailsModel>> =
        detailsDao.getDetailsItem(id)
            .map { it.asDomainModel() }

}