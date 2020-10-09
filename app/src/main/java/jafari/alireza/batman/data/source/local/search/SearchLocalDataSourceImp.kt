package jafari.alireza.batman.data.source.local.search

import com.example.android.devbyteviewer.database.SearchEntity
import javax.inject.Inject

class SearchLocalDataSourceImp @Inject constructor(
    private val searchDao: SearchDao
) : SearchLocalDataSource {

    override fun getSearchList() = searchDao.getSearchList()
    override suspend fun saveAll(searchList: List<SearchEntity>?) {
        searchDao.insertAll(searchList)
    }


}