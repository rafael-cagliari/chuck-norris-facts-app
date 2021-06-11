package com.rafael.data.persistance


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rafael.data.model.ChuckNorrisFactDBItem
import io.reactivex.Single

@Dao
interface FactsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFactToDB(fact:ChuckNorrisFactDBItem): Single<Long>

    @Delete
    fun deleteFactFromDB(fact:ChuckNorrisFactDBItem)

}