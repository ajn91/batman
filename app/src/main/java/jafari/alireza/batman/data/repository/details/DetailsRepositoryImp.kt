package jafari.alireza.batman.data.repository.search

import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.data.domain.derails.DetailsModel
import jafari.alireza.batman.data.source.local.details.DetailsDao
import jafari.alireza.batman.data.source.local.details.asDomainModel
import jafari.alireza.batman.data.source.remote.pojo.details.asDatabaseEntity
import jafari.alireza.batman.utils.NetworkUtil
import jafari.alireza.foursquare.data.remote.api.ApiService
import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    val apiService: ApiService,
    val detailsDao: DetailsDao,
    val networkUtil: NetworkUtil
) : DetailsRepository {

    override fun getDetails(id: String): Observable<DetailsModel> {

        val hasConnection = networkUtil.isConnectedToInternet()
//        var observableFromApi: Observable<List<SearchModel>>? = null
        if (hasConnection)
//            observableFromApi =
            getSearchFromApi(id)
//        }
//        val observableFromDb = getSearchFromDb(id)

//        return if (hasConnection) Observable.concatArrayEager(observableFromDb,observableFromApi)
//        else observableFromDb
        return getSearchFromDb(id)
    }

    fun getSearchFromApi(id: String) =
        apiService.getDetails(id).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ response ->
                Log.d("LOG", "getSearchFromApi: ")
                detailsDao.insertDetailsItem(response.asDatabaseEntity())

            }, { error ->
//        messageStringLive.value = error.message
            })
//        apiService.getSearch()
//            .doOnNext {
//                Log.d("LOG", "getSearchFromApi: ")
//                searchDao.insertAll(it.asDatabaseModel())
//
//            }.map { it.asDomainModel() }


    fun getSearchFromDb(id: String): Observable<DetailsModel> =
        detailsDao.getDetailsItem(id).map { it.asDomainModel() }


}