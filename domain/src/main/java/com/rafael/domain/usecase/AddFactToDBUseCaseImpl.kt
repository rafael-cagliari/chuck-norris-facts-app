package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.DataBaseRepository
import io.reactivex.Single

class AddFactToDBUseCaseImpl(val repository: DataBaseRepository):AddFactToDBUseCase {
    override fun addFactToDB(fact: ChuckNorrisFact): Single<Long> {
        return repository.addFactToDB(fact)
    }
}