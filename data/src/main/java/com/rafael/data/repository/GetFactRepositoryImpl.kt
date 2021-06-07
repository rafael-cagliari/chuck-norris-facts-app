package com.rafael.data.repository

import com.rafael.data.datasource.GetFactDataSourceImpl
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.GetFactRepository
import io.reactivex.Single

class GetFactRepositoryImpl(val datasource: GetFactDataSourceImpl
                            ):GetFactRepository {
    override fun getFact(): Single<ChuckNorrisFact> {
       return datasource.getFact().map { it.mapToDomain() }
    }
}