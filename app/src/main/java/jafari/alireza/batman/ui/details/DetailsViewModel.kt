package jafari.alireza.batman.ui.details


import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.repository.details.DetailsRepository
import jafari.alireza.batman.data.source.remote.ResponseStatus
import jafari.alireza.batman.ui.base.BaseViewModel

@ActivityRetainedScoped
class DetailsViewModel @ViewModelInject
constructor(
    val detailsRepository: DetailsRepository,
    @ApplicationContext val context: Context
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

