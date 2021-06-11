package com.rafael.domain.repository

import com.rafael.domain.model.ChuckNorrisFact
import io.reactivex.Single

interface AddFactToDBRepository {
    fun addFactToDB(fact:ChuckNorrisFact): Single<Long>
}