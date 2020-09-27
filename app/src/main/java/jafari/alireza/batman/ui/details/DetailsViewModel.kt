package jafari.alireza.foursquare.ui.search


import android.app.Application
import jafari.alireza.batman.data.repository.search.DetailsRepository
import jafari.alireza.batman.ui.base.BaseViewModel
import javax.inject.Inject

class DetailsViewModel @Inject
constructor(
    val detailsRepository: DetailsRepository,
    val application: Application,
) : BaseViewModel() {


}

