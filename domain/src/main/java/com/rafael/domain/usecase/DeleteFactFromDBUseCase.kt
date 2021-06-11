package com.rafael.domain.usecase

import io.reactivex.Single

interface DeleteFactFromDBUseCase {
    fun deleteFactFromDB(id:String): Single<Int>
}