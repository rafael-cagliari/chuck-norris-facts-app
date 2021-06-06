package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisResponse
import com.rafael.data.network.RetrofitInstance
import io.reactivex.Single

class RandomFactRequestDataSourceImpl(val retrorfit : RetrofitInstance): RandomFactRequestDataSource {
    override fun requestRandomFact(): Single<ChuckNorrisResponse> {
       return retrorfit.service.getRandomFact()
    }

}