package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisResponse
import io.reactivex.Single

interface RandomFilteredFactRequestDataSource {
    fun requestRandomFilteredFact(category:String): Single<ChuckNorrisResponse>
}