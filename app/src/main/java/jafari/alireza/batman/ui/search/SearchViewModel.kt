package jafari.alireza.batman.ui.search


import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import jafari.alireza.batman.data.domain.search.SearchModel
import jafari.alireza.batman.data.repository.search.SearchRepository
import jafari.alireza.batman.ui.base.BaseViewModel
import jafari.alireza.batman.utils.DirectionParamName

@ActivityRetainedScoped
class SearchViewModel
@ViewModelInject
constructor(
    val searchRepository: SearchRepository,
    @ApplicationContext val context: Context,
) : BaseViewModel() {

    val itemsLive = searchRepository.getSearch()
    val _directToPageLive = MutableLiveData<DirectionParamName>()
    val directToPageLive: LiveData<DirectionParamName>
        get() = _directToPageLive


    fun onItemClick(item: SearchModel) {
        _directToPageLive.value = DirectionParamName.DetailsParams(item.imdbID)
    }

    fun onPageChanged() {
        _directToPageLive.value = null
    }

}

