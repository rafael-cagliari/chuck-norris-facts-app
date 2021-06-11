package com.rafael.domain.repository

import com.rafael.domain.model.ChuckNorrisFact
import io.reactivex.Single

interface DataBaseRepository {
    fun addFactToDB(fact:ChuckNorrisFact): Single<Long>

    fun deleteFactFromDB(id:String):Single<Int>

    fun readAllDB():Single<List<ChuckNorrisFact>>
}