package com.rafael.data.services

import com.rafael.data.model.ChuckNorrisResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitChuckNorrisFactRequest {

    @GET("random")
    fun getRandomFact(): Single<ChuckNorrisResponse>

    @GET("{category}")
    fun getRandomFilteredFact(
        @Path("category") category: String
    ): Single<ChuckNorrisResponse>

}