package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.GetFactRepository
import io.reactivex.Single

class GetFilteredFactUseCaseImpl(val repository: GetFactRepository):GetFilteredFactUseCase {
    override fun getFilteredFact(category: String): Single<ChuckNorrisFact> {
       return repository.getFilteredFact(category)
    }
}