package com.rafael.data.datasource

import android.util.Log
import com.rafael.data.model.ChuckNorrisResponse
import com.rafael.data.network.RetrofitInstance
import io.reactivex.Single
import java.lang.Exception

class GetFactDataSourceImpl(val retrorfit : RetrofitInstance): GetFactDataSource {

    override fun getFilteredFact(category: String): Single<ChuckNorrisResponse> {
        return retrorfit.service.getFilteredFact(category)
    }

    override fun getFact(): Single<ChuckNorrisResponse> {
       return retrorfit.service.getFact()
    }


}