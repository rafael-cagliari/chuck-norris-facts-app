package com.rafael.data.repository


import android.content.Context
import com.rafael.data.datasource.AddFactToDBDataSource
import com.rafael.data.datasource.AddFactToDBDataSourceImpl
import com.rafael.data.datasource.GetFactDataSource
import com.rafael.data.model.ChuckNorrisFactDBItem
import com.rafael.data.persistance.FactsDataBase
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.AddFactToDBRepository

class AddFactToDBRepositoryImpl(val datasource:AddFactToDBDataSource):AddFactToDBRepository {
    override fun addFactToDB(fact:ChuckNorrisFact){
        
        val convertedFactDB =
            ChuckNorrisFactDBItem(
                id = fact.id,
                categories = fact.categories?.get(0),
                value = fact.value
            )
        
        return datasource.addFactToDB(convertedFactDB)
    }
}