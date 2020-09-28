package jafari.alireza.batman.data.repository.search

import android.content.Context
import com.example.android.devbyteviewer.database.asDomainModel
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.R
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.data.source.local.search.SearchDao
import jafari.alireza.batman.data.source.remote.Resource
import jafari.alireza.batman.data.source.remote.api.ApiService
import jafari.alireza.batman.data.source.remote.pojo.search.asDatabaseEntity
import jafari.alireza.batman.utils.NetworkUtil
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    val apiService: ApiService,
    val searchDao: SearchDao,
    val networkUtil: NetworkUtil,
    val context: Context
) : SearchRepository {

    override fun getSearch(): Flowable<Resource<List<SearchModel>>> {

        val hasConnection = networkUtil.isConnectedToInternet()
        if (hasConnection)
            getSearchFromApi()

        return getSearchFromDb().map {
            if (it.size == 0) {
                if (hasConnection)
                    Resource.error(context.getString(R.string.empty_data), it)
                else
                    Resource.error(context.getString(R.string.empty_data_no_network), it)
            } else
                Resource.success(it)
        }.startWith(Resource.loading(null))
            .onErrorReturn {
                Resource.error(it.message ?: "error", null)
            }


    }

    fun getSearchFromApi() =
        apiService.getSearch().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ response ->
                searchDao.insertAll(response.asDatabaseEntity())

            }, { error ->
            })


    fun getSearchFromDb(): Flowable<List<SearchModel>> =
        searchDao.getSearches().map {
            it.asDomainModel()
        }

}