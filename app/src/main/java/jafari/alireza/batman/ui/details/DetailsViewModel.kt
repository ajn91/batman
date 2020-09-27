package jafari.alireza.foursquare.ui.search


import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.repository.details.DetailsRepository
import jafari.alireza.batman.data.source.remote.Resource
import jafari.alireza.batman.ui.base.BaseViewModel
import jafari.alireza.batman.utils.NetworkUtil
import javax.inject.Inject

class DetailsViewModel @Inject
constructor(
    val detailsRepository: DetailsRepository,
    val application: Application,
    val networkUtil: NetworkUtil,
    val context: Context
) : BaseViewModel() {
    lateinit var id: String
    val detailsResourceLive = MutableLiveData<Resource<DetailsModel>>()
    val detailsLive: LiveData<DetailsModel> = Transformations.map(detailsResourceLive) {
        it.data
    }


    fun getDetails(id: String) {
        if (!this::id.isInitialized) {
            this.id = id
            addToDisposable(
                detailsRepository.getDetails(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnComplete {
//                        if (detailsLive.value != null)
//                            return@doOnComplete
//                        if (networkUtil.isConnectedToInternet())
//                            detailsResourceLive.postValue(
//                                Resource.error(
//                                    context.getString(R.string.empty_data),
//                                    null
//                                )
//                            )
//                        else
//                            detailsResourceLive.postValue(
//                                Resource.error(
//                                    context.getString(R.string.empty_data_no_network),
//                                    null
//                                )
//                            )
//
//                    }
                    .subscribe({ response ->
                        detailsResourceLive.postValue(response as Resource<DetailsModel>?)

                    }, { error ->
                        messageStringLive.value = error.message
                    })
            )

        }


    }


}

