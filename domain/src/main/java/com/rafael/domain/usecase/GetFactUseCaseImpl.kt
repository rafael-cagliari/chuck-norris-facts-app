package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.GetFactRepository
import io.reactivex.Single

class GetFactUseCaseImpl(val repository: GetFactRepository):GetFactUseCase {
    override fun getFact(): Single<ChuckNorrisFact> {
        return repository.getFact()
    }
}