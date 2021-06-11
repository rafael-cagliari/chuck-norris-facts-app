package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.AddFactToDBRepository
import io.reactivex.Single

class AddFactToDBUseCaseImpl(val repository: AddFactToDBRepository):AddFactToDBUseCase {
    override fun addFactToDB(fact: ChuckNorrisFact): Single<Long> {
        return repository.addFactToDB(fact)
    }
}