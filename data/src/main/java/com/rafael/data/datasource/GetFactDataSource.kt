package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisResponse
import io.reactivex.Single

interface GetFactDataSource {

    fun getFact(): Single<ChuckNorrisResponse>

    fun getFilteredFact(category:String): Single<ChuckNorrisResponse>

}