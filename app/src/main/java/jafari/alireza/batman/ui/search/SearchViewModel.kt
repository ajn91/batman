package jafari.alireza.foursquare.ui.search


import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.data.repository.search.SearchRepository
import jafari.alireza.batman.ui.base.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject
constructor(
    val searchRepository: SearchRepository,
    val application: Application,
) : BaseViewModel() {
    val itemsLive = MutableLiveData<List<SearchModel>>()


    init {
        getSearch()
    }

    private fun getSearch() {

        addToDisposable(
            searchRepository.getSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.d("LOG", "getSearch: ${response.size}")
                    if (response != null)
                        itemsLive.postValue(response)

                }, { error ->
                    messageStringLive.value = error.message
                })
        )
    }


}

