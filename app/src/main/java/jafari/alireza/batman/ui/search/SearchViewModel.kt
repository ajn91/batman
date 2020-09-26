package jafari.alireza.foursquare.ui.search


import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.android.devbyteviewer.domain.SearchModel
import jafari.alireza.batman.data.repository.SearchRepository
import jafari.alireza.batman.ui.base.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject
constructor(
    val repository: SearchRepository,
    val application: Application,
) : BaseViewModel() {
    val itemsLive = MutableLiveData<MutableList<SearchModel>>()


    init {
    }


}

