package jafari.alireza.batman.ui.details


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.repository.details.DetailsRepository
import jafari.alireza.batman.data.source.remote.ResponseStatus
import jafari.alireza.batman.ui.base.BaseViewModel
import javax.inject.Inject

class DetailsViewModel @Inject
constructor(
    val detailsRepository: DetailsRepository,
    val context: Context
) : BaseViewModel() {
    lateinit var id: String
    val _detailsResourceLive = MutableLiveData<Pair<ResponseStatus, DetailsModel?>>()
    val detailsResourceLive: LiveData<Pair<ResponseStatus, DetailsModel?>>
        get() = _detailsResourceLive
    val detailsLive: LiveData<DetailsModel>
        get() = Transformations.map(_detailsResourceLive) {
            it.second
        }


    fun getDetails(id: String) {
//        if (!this::id.isInitialized) {
        this.id = id
        addToDisposable(
            detailsRepository.getDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    _detailsResourceLive.postValue(response)

                }, { error ->
                    _messageStringLive.value = error.message
                })
        )
        }
//    }


}

