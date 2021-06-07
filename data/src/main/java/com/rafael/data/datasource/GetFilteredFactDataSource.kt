package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisResponse
import io.reactivex.Single

interface GetFilteredFactDataSource {
    fun getFilteredFact(category:String): Single<ChuckNorrisResponse>
}