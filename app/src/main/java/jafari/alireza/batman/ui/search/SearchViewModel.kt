package jafari.alireza.foursquare.ui.search


import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.data.repository.search.SearchRepository
import jafari.alireza.batman.data.source.remote.Resource
import jafari.alireza.batman.ui.base.BaseViewModel
import jafari.alireza.batman.utils.DirectionParamName
import javax.inject.Inject

class SearchViewModel @Inject
constructor(
    val searchRepository: SearchRepository,
    val application: Application,
) : BaseViewModel() {
    val itemsLive = MutableLiveData<Resource<List<SearchModel>>>()
    val directToPageLive = MutableLiveData<DirectionParamName>()


    init {
        getSearch()
    }

    private fun getSearch() {

        addToDisposable(
            searchRepository.getSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    itemsLive.postValue(response)
                }, { error ->

                    messageStringLive.value = error.message
                })
        )
    }

    fun onItemClick(position: Int) {
        val id = itemsLive.value?.data?.get(position)?.imdbID
        if (id != null) {
            directToPageLive.value = DirectionParamName.DetailsParams(id)
        }

    }

}

