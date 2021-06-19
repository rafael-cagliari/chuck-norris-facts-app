package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.repository.DataBaseRepository
import io.reactivex.Single

class ReadAllDBUseCaseImpl(val repository: DataBaseRepository):ReadAllDBUseCase {
    override fun readAllDB(): Single<List<ChuckNorrisFact>> {
        return repository.readAllDB()

    }
}