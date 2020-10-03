package jafari.alireza.batman.data.repository.search

import io.reactivex.Flowable
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.data.source.remote.ResponseStatus

interface SearchRepository {

    fun getSearch(): Flowable<Pair<ResponseStatus, List<SearchModel>?>>


}