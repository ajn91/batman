package jafari.alireza.batman.data.repository.details

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.R
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.source.local.details.DetailsDao
import jafari.alireza.batman.data.source.local.details.entity.asDomainModel
import jafari.alireza.batman.data.source.remote.ResponseStatus
import jafari.alireza.batman.data.source.remote.api.ApiService
import jafari.alireza.batman.data.source.remote.pojo.details.asDatabaseEntity
import jafari.alireza.batman.utils.NetworkUtil
import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    val apiService: ApiService,
    val detailsDao: DetailsDao,
    val networkUtil: NetworkUtil,
    val context: Context
) : DetailsRepository {

    override fun getDetails(id: String): Flowable<Pair<ResponseStatus, DetailsModel?>> {
        val hasConnection = networkUtil.isConnectedToInternet()
        if (hasConnection)
            getDetailsFromApi(id)

        return getDetailsFromDb(id).map {
            if (it.size == 0) {
                if (hasConnection)
                    Pair(
                        ResponseStatus.ERROR(context.getString(R.string.empty_data)),
                        null
                    )
//                    Resource.error(context.getString(R.string.empty_data), null)
                else
                    Pair(
                        ResponseStatus.ERROR(context.getString(R.string.empty_data_no_network)),
                        null
                    )

//                        Resource.error(context.getString(R.string.empty_data_no_network), null)
            } else
                Pair(ResponseStatus.SUCCESS(), it[0])
//                Resource.success(it[0])
        }.startWith(
            Pair(ResponseStatus.LOADING(), null)
        )
            .onErrorReturn {
                Pair(
                    ResponseStatus.ERROR(it.message ?: "error"),
                    null
                )


//                Resource.error(it.message ?: "error", null)
            }


    }

    fun getDetailsFromApi(id: String) =
        apiService.getDetails(id).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ response ->
                detailsDao.insertDetailsItem(response.asDatabaseEntity())

            }, { error ->
            })

    fun getDetailsFromDb(id: String): Flowable<List<DetailsModel>> =
        detailsDao.getDetailsItem(id)
            .map { it.asDomainModel() }

}