package jafari.alireza.batman.data.source.local.details

import androidx.lifecycle.LiveData
import jafari.alireza.batman.data.source.local.details.entity.DetailsEntity

interface DetailsLocalDataSource {

    fun getDetails(id: String): LiveData<List<DetailsEntity>>
    suspend fun saveDetails(item: DetailsEntity?)

}