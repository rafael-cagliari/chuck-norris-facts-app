package com.rafael.data.persistance


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rafael.data.model.ChuckNorrisFactDBItem
import com.rafael.domain.model.ChuckNorrisFact

@Dao
interface FactsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFactToDB(fact:ChuckNorrisFactDBItem)

    @Delete
    fun deleteFactFromDB(fact:ChuckNorrisFactDBItem)

}