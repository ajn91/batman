package jafari.alireza.batman.data.source.local.details


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import jafari.alireza.batman.data.source.local.details.entity.DetailsEntity


@Dao
interface DetailsDao {
    @Query("select * from DetailsEntity WHERE imdbID = :id")
    fun getDetailsItem(id: String): Flowable<List<DetailsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailsItem(item: DetailsEntity)
}
