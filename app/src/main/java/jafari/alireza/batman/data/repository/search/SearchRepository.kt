package jafari.alireza.batman.data.repository.search

import io.reactivex.Flowable
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.data.source.remote.Resource

interface SearchRepository {

    fun getSearch(): Flowable<Resource<List<SearchModel>>>


}