package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisResponse
import com.rafael.data.network.RetrofitInstance
import io.reactivex.Single

class GetFactDataSourceImpl(val retrorfit : RetrofitInstance): GetFactDataSource {

    override fun getFilteredFact(category: String): Single<ChuckNorrisResponse> {
        return retrorfit.service.getFilteredFact(category)
    }

    override fun getFact(): Single<ChuckNorrisResponse> {
       return retrorfit.service.getFact()
    }


}