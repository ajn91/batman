package jafari.alireza.batman.data.source.remote.search

import com.example.rickandmorty.data.remote.BaseRemoteDataSource
import jafari.alireza.batman.data.source.remote.api.ApiService
import javax.inject.Inject

class SearchRemoteRemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService
) : BaseRemoteDataSource(), SearchRemoteDataSource {

    override suspend fun getSearch() = getResult { apiService.getSearchList() }
}