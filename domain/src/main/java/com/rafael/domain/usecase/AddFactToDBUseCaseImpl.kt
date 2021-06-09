package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.AddFactToDBRepository
import com.rafael.domain.repository.GetFilteredFactRepository

class AddFactToDBUseCaseImpl(val repository: AddFactToDBRepository):AddFactToDBUseCase {
    override fun addFactToDB(fact: ChuckNorrisFact) {
        return repository.addFactToDB(fact)
    }
}