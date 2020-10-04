package jafari.alireza.batman.ui.details


import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.repository.details.DetailsRepository
import jafari.alireza.batman.data.source.remote.ResponseStatus
import jafari.alireza.batman.ui.base.BaseViewModel
import jafari.alireza.batman.utils.DetailsParams

@ActivityRetainedScoped
class DetailsViewModel @ViewModelInject
constructor(
    val detailsRepository: DetailsRepository,
    @Assisted savedStateHandle: SavedStateHandle,
    @ApplicationContext val context: Context
) : BaseViewModel() {
    val id: String? =
        savedStateHandle[DetailsParams.ID_Name]

    val _detailsResourceLive = MutableLiveData<Pair<ResponseStatus, DetailsModel?>>()
    val detailsResourceLive: LiveData<Pair<ResponseStatus, DetailsModel?>>
        get() = _detailsResourceLive
    val detailsLive: LiveData<DetailsModel>
        get() = Transformations.map(_detailsResourceLive) {
            it.second
        }

    init {
        getDetails()

    }

    fun getDetails() {
//        if (!this::id.isInitialized) {


        if (id == null) {
            _messageStringLive.value = "error"
            return
        }
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


}

