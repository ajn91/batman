package jafari.alireza.batman.data.source.remote.details

import com.example.rickandmorty.data.remote.BaseRemoteDataSource
import jafari.alireza.batman.data.source.remote.api.ApiService
import javax.inject.Inject

class DetailsRemoteRemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService
) : BaseRemoteDataSource(), DetailsRemoteDataSource {

    override suspend fun getDetails(id: String) = getResult { apiService.getDetails(id) }
}