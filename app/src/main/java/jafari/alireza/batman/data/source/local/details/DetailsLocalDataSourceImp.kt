package jafari.alireza.batman.data.source.local.details

import jafari.alireza.batman.data.source.local.details.entity.DetailsEntity
import javax.inject.Inject

class DetailsLocalDataSourceImp @Inject constructor(
    private val detailsDao: DetailsDao
) : DetailsLocalDataSource {
    override fun getDetails(id: String) = detailsDao.getDetails(id)

    override suspend fun saveDetails(item: DetailsEntity?) {
        detailsDao.insertDetails(item)
    }


}