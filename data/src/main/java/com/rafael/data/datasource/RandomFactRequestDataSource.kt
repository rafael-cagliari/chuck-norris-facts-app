package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisResponse
import io.reactivex.Single

interface RandomFactRequestDataSource {

    fun requestRandomFact(): Single<ChuckNorrisResponse>

}