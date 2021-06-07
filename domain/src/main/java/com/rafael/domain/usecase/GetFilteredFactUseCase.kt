package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import io.reactivex.Single

interface GetFilteredFactUseCase {
    fun getFilteredFact(category:String):Single<ChuckNorrisFact>
}