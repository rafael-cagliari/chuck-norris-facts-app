package com.rafael.data.repository

import com.rafael.data.datasource.GetFactDataSource
import com.rafael.data.datasource.GetFactDataSourceImpl
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.GetFactRepository
import io.reactivex.Single

class GetFactRepositoryImpl(val datasource: GetFactDataSource
                            ):GetFactRepository {
    override fun getFact(): Single<ChuckNorrisFact> {
       return datasource.getFact().map { it.mapToDomain() }
    }

    override fun getFilteredFact(category: String): Single<ChuckNorrisFact> {
        return datasource.getFilteredFact(category).map { it.mapToDomain() }
    }
}