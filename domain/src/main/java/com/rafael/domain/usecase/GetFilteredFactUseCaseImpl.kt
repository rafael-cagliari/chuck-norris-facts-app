package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.GetFilteredFactRepository
import io.reactivex.Single

class GetFilteredFactUseCaseImpl(val repository:GetFilteredFactRepository):GetFilteredFactUseCase {
    override fun getFilteredFact(category: String): Single<ChuckNorrisFact> {
       return repository.getFilteredFact(category)
    }
}