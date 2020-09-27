package jafari.alireza.batman.data.repository.search

import io.reactivex.Observable
import jafari.alireza.batman.data.domain.search.SearchModel

interface SearchRepository {

    fun getSearch(): Observable<List<SearchModel>>


}