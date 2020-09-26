package jafari.alireza.batman.data.repository

import io.reactivex.Observable
import jafari.alireza.batman.data.domain.SearchModel

interface SearchRepository {

    fun getSearch(): Observable<List<SearchModel>>


}