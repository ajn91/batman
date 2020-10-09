package jafari.alireza.batman.data.repository.search

import androidx.lifecycle.LiveData
import jafari.alireza.batman.data.Resource
import jafari.alireza.batman.data.domain.search.SearchModel

interface SearchRepository {

    fun getSearch(): LiveData<Resource<List<SearchModel>?>>


}