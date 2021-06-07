package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisResponse
import com.rafael.data.network.RetrofitInstance
import io.reactivex.Single

class GetFilteredFactDataSourceImpl(val retrofit: RetrofitInstance):GetFilteredFactDataSource {

    override fun getFilteredFact(category: String): Single<ChuckNorrisResponse> {
        return retrofit.service.getFilteredFact(category)
    }
}