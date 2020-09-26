package jafari.alireza.batman.data.repository

import android.util.Log
import com.example.android.devbyteviewer.database.asDomainModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.data.domain.SearchModel
import jafari.alireza.batman.data.source.local.search.SearchDao
import jafari.alireza.batman.data.source.remote.model.search.asDatabaseModel
import jafari.alireza.batman.utils.NetworkUtil
import jafari.alireza.foursquare.data.remote.api.ApiService
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    val apiService: ApiService,
    val searchDao: SearchDao,
    val networkUtil: NetworkUtil
) : SearchRepository {

    override fun getSearch(): Observable<List<SearchModel>> {

        val hasConnection = networkUtil.isConnectedToInternet()
//        var observableFromApi: Observable<List<SearchModel>>? = null
        if (hasConnection)
//            observableFromApi =
            getSearchFromApi()
//        }
        val observableFromDb = getSearchFromDb()

//        return if (hasConnection) Observable.concatArrayEager(observableFromDb,observableFromApi)
//        else observableFromDb
        return getSearchFromDb()
    }

    fun getSearchFromApi() =
        apiService.getSearch().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ response ->
                Log.d("LOG", "getSearchFromApi: ")

                searchDao.insertAll(response.asDatabaseModel())

            }, { error ->
//        messageStringLive.value = error.message
            })
//        apiService.getSearch()
//            .doOnNext {
//                Log.d("LOG", "getSearchFromApi: ")
//                searchDao.insertAll(it.asDatabaseModel())
//
//            }.map { it.asDomainModel() }


    fun getSearchFromDb(): Observable<List<SearchModel>> =
        searchDao.getSearches().map {
            Log.d("LOG", "getSearchFromDb: ")
            it.asDomainModel()
        }

}