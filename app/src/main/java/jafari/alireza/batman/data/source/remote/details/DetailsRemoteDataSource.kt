package jafari.alireza.batman.data.source.remote.details

import jafari.alireza.batman.data.Resource
import jafari.alireza.batman.data.source.remote.details.pojo.DetailsNetwork

interface DetailsRemoteDataSource {

    suspend fun getDetails(id: String): Resource<DetailsNetwork?>
}