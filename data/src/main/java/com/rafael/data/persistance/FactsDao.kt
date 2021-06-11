package com.rafael.data.persistance


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafael.data.model.ChuckNorrisFactDBItem
import io.reactivex.Single

@Dao
interface FactsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFactToDB(fact:ChuckNorrisFactDBItem): Single<Long>

    @Query("DELETE FROM facts_table WHERE id==:id")
    fun deleteFactFromDB(id:String):Single<Int>

    @Query("SELECT * FROM facts_table ORDER BY id ASC")
    fun readAllDB(): Single<List<ChuckNorrisFactDBItem>>

}