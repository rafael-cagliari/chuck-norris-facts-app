package com.rafael.domain.usecase

import com.rafael.domain.model.ChuckNorrisFact
import io.reactivex.Single

interface AddFactToDBUseCase {
    fun addFactToDB(fact:ChuckNorrisFact)
}