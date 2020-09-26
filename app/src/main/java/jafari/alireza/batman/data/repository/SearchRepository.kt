package jafari.alireza.batman.data.repository

import com.example.android.devbyteviewer.database.asDomainModel
import com.example.android.devbyteviewer.domain.SearchModel
import io.reactivex.Observable
import jafari.alireza.batman.data.source.local.search.SearchDao
import jafari.alireza.batman.data.source.remote.model.search.asDatabaseModel
import jafari.alireza.batman.data.source.remote.model.search.asDomainModel
import jafari.alireza.batman.utils.NetworkUtil
import jafari.alireza.foursquare.data.remote.api.ApiService
import javax.inject.Inject

class SearchRepository @Inject constructor(
    val apiService: ApiService,
    val searchDao: SearchDao,
    val networkUtil: NetworkUtil
) {

    fun getSearch(): Observable<List<SearchModel>> {

        val hasConnection = networkUtil.isConnectedToInternet()
        var observableFromApi: Observable<List<SearchModel>>? = null
        if (hasConnection) {
            observableFromApi = getSearchFromApi()
        }
        val observableFromDb = getSearchFromDb()

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    fun getSearchFromApi(): Observable<List<SearchModel>> =
        apiService.getSearch()
            .doOnNext {
                searchDao.insertAll(it.asDatabaseModel())

            }.map { it.asDomainModel() }


    fun getSearchFromDb(): Observable<List<SearchModel>> =
        searchDao.getSearches().map {
            it.asDomainModel()
        }

}