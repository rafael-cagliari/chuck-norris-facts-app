package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import io.reactivex.Single

interface RandomFilteredFactRequest {
    fun getRandomFilteredFact(category:String):Single<ChuckNorrisFact>
}