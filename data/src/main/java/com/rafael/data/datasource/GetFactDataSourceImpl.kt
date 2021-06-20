package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisResponse
import com.rafael.data.services.FactRequestService
import io.reactivex.Single

class GetFactDataSourceImpl(val service : FactRequestService): GetFactDataSource {

    override fun getFilteredFact(category: String): Single<ChuckNorrisResponse> {
        return service.getFilteredFact(category)
    }

    override fun getFact(): Single<ChuckNorrisResponse> {
       return service.getFact()
    }


}