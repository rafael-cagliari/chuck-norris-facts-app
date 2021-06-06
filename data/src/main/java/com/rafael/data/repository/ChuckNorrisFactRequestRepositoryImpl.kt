package com.rafael.data.repository

import com.rafael.data.datasource.RandomFactRequestDataSourceImpl
import com.rafael.data.datasource.RandomFilteredFactRequestDataSource
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.ChuckNorrisFactRequestRepository
import io.reactivex.Single

class ChuckNorrisFactRequestRepositoryImpl(val datasource1 : RandomFactRequestDataSourceImpl,
                                           val datasource2:RandomFilteredFactRequestDataSource):ChuckNorrisFactRequestRepository {
    override fun requestRandomFact(): Single<ChuckNorrisFact> {
       return datasource1.requestRandomFact().map { it.mapToDomain() }
    }

    override fun requestRandomFilteredFact(category: String): Single<ChuckNorrisFact> {
        TODO("Not yet implemented")
    }


}