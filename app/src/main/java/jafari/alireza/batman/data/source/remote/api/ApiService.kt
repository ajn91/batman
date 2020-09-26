package jafari.alireza.foursquare.data.remote.api

import io.reactivex.Observable
import jafari.alireza.batman.data.source.remote.model.search.SearchNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET()
    fun getSearch(
        @Query("s") search: String = "batman",
    ): Observable<SearchNetworkResponse>


}
