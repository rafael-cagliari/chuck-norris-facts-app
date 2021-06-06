package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.ChuckNorrisFactRequestRepository
import io.reactivex.Single

class RandomFilteredFactRequestImpl(val repository:ChuckNorrisFactRequestRepository):RandomFilteredFactRequest {
    override fun getRandomFilteredFact(category: String): Single<ChuckNorrisFact> {
       return repository.requestRandomFilteredFact(category)
    }
}