package jafari.alireza.batman.data.repository.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import dagger.hilt.android.qualifiers.ApplicationContext
import jafari.alireza.batman.R
import jafari.alireza.batman.data.Resource
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.performGetOperation
import jafari.alireza.batman.data.source.local.details.DetailsLocalDataSource
import jafari.alireza.batman.data.source.local.details.entity.asDomainModel
import jafari.alireza.batman.data.source.remote.details.DetailsRemoteDataSource
import jafari.alireza.batman.data.source.remote.details.pojo.DetailsNetwork
import jafari.alireza.batman.data.source.remote.details.pojo.asDatabaseEntity
import jafari.alireza.batman.utils.NetworkUtil
import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    val remoteDataSource: DetailsRemoteDataSource,
    val localDataSource: DetailsLocalDataSource,
    val networkUtil: NetworkUtil,
    @ApplicationContext val context: Context
) : DetailsRepository {


    override fun getDetails(id: String): LiveData<Resource<DetailsModel?>> {
        val networkStatus = networkUtil.isConnectedToInternet()
        return performGetOperation<DetailsModel, DetailsNetwork>(
            if (networkStatus) context.getString(R.string.no_item_available) else context.getString(
                R.string.no_item_no_internet
            ),
            localFetch = {
                localDataSource.getDetails(id).map {
                    if (it.size > 0) it.get(0).asDomainModel() else null
                }
            },
            remoteFetch = if (networkStatus) ({ remoteDataSource.getDetails(id) }) else null,
            saveRemoteResult = { localDataSource.saveDetails(it.asDatabaseEntity()) }
        )
    }


}