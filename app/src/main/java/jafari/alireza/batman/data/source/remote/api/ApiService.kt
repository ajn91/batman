package jafari.alireza.batman.data.source.remote.api

import jafari.alireza.batman.data.source.remote.details.pojo.DetailsNetwork
import jafari.alireza.batman.data.source.remote.search.pojo.SearchNetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun getSearchList(
        @Query("s") search: String = "batman",
    ): Response<SearchNetworkResponse>

    @GET("/")
    suspend fun getDetails(
        @Query("i") id: String
    ): Response<DetailsNetwork>
}
