package jafari.alireza.batman.data.source.remote.search

import jafari.alireza.batman.data.Resource
import jafari.alireza.batman.data.source.remote.search.pojo.SearchNetworkResponse

interface SearchRemoteDataSource {

    suspend fun getSearch(): Resource<SearchNetworkResponse?>
}