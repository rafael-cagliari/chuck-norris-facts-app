package com.rafael.data.repository


import com.rafael.data.datasource.AddFactToDBDataSource
import com.rafael.data.model.ChuckNorrisFactDBItem
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.AddFactToDBRepository
import io.reactivex.Single

class AddFactToDBRepositoryImpl(val datasource:AddFactToDBDataSource):AddFactToDBRepository {
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
}