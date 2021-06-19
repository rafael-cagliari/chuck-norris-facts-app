package com.rafael.domain.repository

import com.rafael.domain.model.ChuckNorrisFact
import io.reactivex.Single

interface GetFactRepository {
    fun getFact(): Single<ChuckNorrisFact>

    fun getFilteredFact(category:String): Single<ChuckNorrisFact>
}