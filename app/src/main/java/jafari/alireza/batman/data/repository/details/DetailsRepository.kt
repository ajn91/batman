package jafari.alireza.batman.data.repository.search

import io.reactivex.Observable
import jafari.alireza.batman.data.domain.derails.DetailsModel

interface DetailsRepository {

    fun getDetails(id: String): Observable<DetailsModel>


}