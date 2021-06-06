package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisResponse
import com.rafael.data.network.RetrofitInstance
import io.reactivex.Single

class RandomFilteredFactRequestDataSourceImpl(val retrofit: RetrofitInstance):RandomFilteredFactRequestDataSource {

    override fun requestRandomFilteredFact(category: String): Single<ChuckNorrisResponse> {
        return retrofit.service.getRandomFilteredFact(category)
    }
}