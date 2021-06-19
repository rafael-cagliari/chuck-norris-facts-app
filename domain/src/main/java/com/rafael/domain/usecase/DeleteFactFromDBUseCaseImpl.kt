package com.rafael.domain.usecase

import com.rafael.domain.repository.DataBaseRepository
import io.reactivex.Single

class DeleteFactFromDBUseCaseImpl(val repository:DataBaseRepository):DeleteFactFromDBUseCase {
    override fun deleteFactFromDB(id: String): Single<Int> {
       return repository.deleteFactFromDB(id)
    }
}