package com.rafael.data.services

import com.rafael.data.model.ChuckNorrisResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitFactRequest {

    @GET("random")
    fun getFact(): Single<ChuckNorrisResponse>

    @GET("random")
    fun getFilteredFact(
        @Query("category") category: String
    ): Single<ChuckNorrisResponse>

}