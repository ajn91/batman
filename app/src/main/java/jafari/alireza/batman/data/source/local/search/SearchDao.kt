package jafari.alireza.batman.data.source.local.search


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.devbyteviewer.database.SearchEntity
import io.reactivex.Flowable


@Dao
interface SearchDao {
    @Query("select * from SearchEntity")
    fun getSearches(): Flowable<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(searches: List<SearchEntity>)
}
