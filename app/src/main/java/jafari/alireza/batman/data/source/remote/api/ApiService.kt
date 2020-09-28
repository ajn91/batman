package jafari.alireza.batman.data.source.remote.api

import io.reactivex.Observable
import jafari.alireza.batman.data.source.remote.pojo.details.DetailsNetwork
import jafari.alireza.batman.data.source.remote.pojo.search.SearchNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    fun getSearch(
        @Query("s") search: String = "batman",
    ): Observable<SearchNetworkResponse>

    @GET("/")
    fun getDetails(
        @Query("i") id: String
    ): Observable<DetailsNetwork>
}
