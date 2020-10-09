package jafari.alireza.batman.data.source.local.search


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.devbyteviewer.database.SearchEntity


@Dao
interface SearchDao {
    @Query("select * from searches")
    fun getSearchList(): LiveData<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(searchList: List<SearchEntity>?)
}
