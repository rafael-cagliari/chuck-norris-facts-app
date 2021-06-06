package com.rafael.data.network

import com.rafael.data.Constants
import com.rafael.data.services.RetrofitChuckNorrisFactRequest
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val service: RetrofitChuckNorrisFactRequest by lazy {
        retrofit.create(RetrofitChuckNorrisFactRequest::class.java)
    }
}