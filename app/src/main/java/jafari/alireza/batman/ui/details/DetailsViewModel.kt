package jafari.alireza.batman.ui.details


import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.switchMap
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import jafari.alireza.batman.data.Resource
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.repository.details.DetailsRepository
import jafari.alireza.batman.ui.base.BaseViewModel
import jafari.alireza.batman.utils.DetailsParams

@ActivityRetainedScoped
class DetailsViewModel @ViewModelInject
constructor(
    val detailsRepository: DetailsRepository,
    @Assisted savedStateHandle: SavedStateHandle,
    @ApplicationContext val context: Context
) : BaseViewModel() {

    private val _id = MutableLiveData<String?>(savedStateHandle[DetailsParams.ID_Name])
    var detailsResourceLive: LiveData<Resource<DetailsModel?>> = _id.switchMap { id ->
        detailsRepository.getDetails(id ?: "")
    }
}

