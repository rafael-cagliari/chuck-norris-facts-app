package com.rafael.data.repository


import com.rafael.data.datasource.DataBaseDataSource
import com.rafael.data.model.ChuckNorrisFactDBItem
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.DataBaseRepository
import io.reactivex.Single

class DataBaseRepositoryImpl(val datasource:DataBaseDataSource):DataBaseRepository {
    override fun addFactToDB(fact:ChuckNorrisFact):Single<Long> {
        
        val convertedFactDB =
            ChuckNorrisFactDBItem(
                id = fact.id,
                categories = if(fact.categories?.size==0)""
                else fact.categories?.get(0),
                value = fact.value
            )
        
        return datasource.addFactToDB(convertedFactDB)
    }

    override fun deleteFactFromDB(id: String): Single<Int> {
       return datasource.deleteFactFromDB(id)
    }

    override fun readAllDB(): Single<List<ChuckNorrisFact>> {
        return datasource.readAllDB()
    }
}