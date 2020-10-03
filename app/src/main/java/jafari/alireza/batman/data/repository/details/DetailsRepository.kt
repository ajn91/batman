package jafari.alireza.batman.data.repository.details

import io.reactivex.Flowable
import jafari.alireza.batman.data.domain.details.DetailsModel
import jafari.alireza.batman.data.source.remote.ResponseStatus

interface DetailsRepository {

    fun getDetails(id: String): Flowable<Pair<ResponseStatus, DetailsModel?>>


}