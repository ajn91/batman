package jafari.alireza.batman.data.source.local.details


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jafari.alireza.batman.data.source.local.details.entity.DetailsEntity


@Dao
interface DetailsDao {
    @Query("select * from details WHERE imdbID = :id")
    fun getDetails(id: String): LiveData<List<DetailsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(item: DetailsEntity?)
}
