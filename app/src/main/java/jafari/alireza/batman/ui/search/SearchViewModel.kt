package jafari.alireza.batman.ui.search


import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.data.repository.search.SearchRepository
import jafari.alireza.batman.data.source.remote.ResponseStatus
import jafari.alireza.batman.ui.base.BaseViewModel
import jafari.alireza.batman.utils.DirectionParamName

@ActivityRetainedScoped
class SearchViewModel
@ViewModelInject
constructor(
    val searchRepository: SearchRepository,
    @ApplicationContext val context: Context,
) : BaseViewModel() {
    val _itemsLive = MutableLiveData<Pair<ResponseStatus, List<SearchModel>?>>()
    val itemsLive: LiveData<Pair<ResponseStatus, List<SearchModel>?>>
        get() = _itemsLive
    val _directToPageLive = MutableLiveData<DirectionParamName>()
    val directToPageLive: LiveData<DirectionParamName>
        get() = _directToPageLive


    init {
        getSearch()
    }

    private fun getSearch() {

        addToDisposable(
            searchRepository.getSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    _itemsLive.postValue(response)
                }, { error ->

                    _messageStringLive.value = error.message
                })
        )
    }

    fun onItemClick(position: Int) {
        val id = _itemsLive.value?.second?.get(position)?.imdbID
        if (id != null) {
            _directToPageLive.value = DirectionParamName.DetailsParams(id)
        }

    }

    fun onPageChanged() {
        _directToPageLive.value = null


    }

}

