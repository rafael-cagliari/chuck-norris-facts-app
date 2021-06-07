package com.rafael.data.repository

import com.rafael.data.datasource.GetFilteredFactDataSource
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.GetFilteredFactRepository
import io.reactivex.Single

class GetFilteredFactRepositoryImpl(val datasource: GetFilteredFactDataSource):GetFilteredFactRepository {

    override fun getFilteredFact(category: String): Single<ChuckNorrisFact> {
        return datasource.getFilteredFact(category).map { it.mapToDomain() }
    }}