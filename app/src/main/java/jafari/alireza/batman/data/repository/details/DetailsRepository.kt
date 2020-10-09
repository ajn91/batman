package jafari.alireza.batman.data.repository.details

import androidx.lifecycle.LiveData
import jafari.alireza.batman.data.Resource
import jafari.alireza.batman.data.domain.details.DetailsModel

interface DetailsRepository {

    fun getDetails(id: String): LiveData<Resource<DetailsModel?>>


}