package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisFactDBItem
import com.rafael.domain.model.ChuckNorrisFact
import io.reactivex.Single

interface DataBaseDataSource {
    fun addFactToDB(fact:ChuckNorrisFactDBItem): Single<Long>

    fun deleteFactFromDB(id:String): Single<Int>

    fun readAllDB(): Single<List<ChuckNorrisFact>>
}