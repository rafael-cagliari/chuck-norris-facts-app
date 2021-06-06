package com.rafael.domain.repository

import com.rafael.domain.model.ChuckNorrisFact
import io.reactivex.Single

interface ChuckNorrisFactRequestRepository {
    fun requestRandomFact(): Single<ChuckNorrisFact>

    fun requestRandomFilteredFact(category:String): Single<ChuckNorrisFact>
}