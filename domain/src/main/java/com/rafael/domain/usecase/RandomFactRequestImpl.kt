package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.ChuckNorrisFactRequestRepository
import io.reactivex.Single

class RandomFactRequestImpl(val repository: ChuckNorrisFactRequestRepository):RandomFactRequest {
    override fun getRandomFact(): Single<ChuckNorrisFact> {
        return repository.requestRandomFact()
    }
}