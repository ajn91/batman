package jafari.alireza.batman.data.source.local.search

import androidx.lifecycle.LiveData
import com.example.android.devbyteviewer.database.SearchEntity

interface SearchLocalDataSource {

    fun getSearchList(): LiveData<List<SearchEntity>>
    suspend fun saveAll(searchList: List<SearchEntity>?)

}