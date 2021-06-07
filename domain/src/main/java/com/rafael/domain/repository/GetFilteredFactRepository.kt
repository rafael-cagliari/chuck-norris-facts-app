package com.rafael.domain.repository

import com.rafael.domain.model.ChuckNorrisFact
import io.reactivex.Single

interface GetFilteredFactRepository {
    fun getFilteredFact(category:String): Single<ChuckNorrisFact>
}