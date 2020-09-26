package jafari.alireza.batman.data.database.search


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.devbyteviewer.database.SearchEntity


@Dao
interface SearchDao {
    @Query("select * from SearchEntity")
    fun getSearches(): LiveData<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(searches: List<SearchEntity>)
}
