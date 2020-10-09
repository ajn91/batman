package jafari.alireza.batman.data.repository.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.android.devbyteviewer.database.asDomainModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.batman.R
import jafari.alireza.batman.data.Resource
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.data.performGetOperation
import jafari.alireza.batman.data.source.local.search.SearchLocalDataSource
import jafari.alireza.batman.data.source.remote.search.SearchRemoteDataSource
import jafari.alireza.batman.data.source.remote.search.pojo.SearchNetworkResponse
import jafari.alireza.batman.data.source.remote.search.pojo.asDatabaseEntity
import jafari.alireza.batman.utils.NetworkUtil
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    val remoteDataSource: SearchRemoteDataSource,
    val localDataSource: SearchLocalDataSource,
    val networkUtil: NetworkUtil,
    @ApplicationContext val context: Context
) : SearchRepository {


    override fun getSearch(): LiveData<Resource<List<SearchModel>?>> {
        val networkStatus = networkUtil.isConnectedToInternet()
        return performGetOperation<List<SearchModel>, SearchNetworkResponse>(
            if (networkStatus) context.getString(R.string.no_item_available) else context.getString(
                R.string.no_item_no_internet
            ),
            localFetch = {
                localDataSource.getSearchList().map {
                    if (it.size > 0) it.asDomainModel() else null
                }
            },
            remoteFetch = if (networkStatus) ({ remoteDataSource.getSearch() }) else null,
            saveRemoteResult = { localDataSource.saveAll(it.asDatabaseEntity()) }
        )
    }

}