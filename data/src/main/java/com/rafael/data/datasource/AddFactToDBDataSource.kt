package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisFactDBItem
import io.reactivex.Single

interface AddFactToDBDataSource {
    fun addFactToDB(fact:ChuckNorrisFactDBItem): Single<Long>
}