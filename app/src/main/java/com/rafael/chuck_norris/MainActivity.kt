package com.rafael.chuck_norris

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rafael.data.datasource.GetFactDataSourceImpl
import com.rafael.data.datasource.GetFilteredFactDataSource
import com.rafael.data.datasource.GetFilteredFactDataSourceImpl
import com.rafael.data.network.RetrofitInstance
import com.rafael.data.repository.GetFactRepositoryImpl
import com.rafael.data.repository.GetFilteredFactRepositoryImpl
import com.rafael.domain.usecase.GetFactUseCaseImpl
import com.rafael.domain.usecase.GetFilteredFactUseCase
import com.rafael.domain.usecase.GetFilteredFactUseCaseImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFact()
        getFilteredFact("dev")
    }

    @SuppressLint("CheckResult")
    fun getFact() {
        GetFactUseCaseImpl(
            GetFactRepositoryImpl(
                GetFactDataSourceImpl(RetrofitInstance())
            )
        ).getFact()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Log.d("result chuck norris", "${response}")
            },
                { throwable ->
                    Log.d("chuck norris error", throwable.localizedMessage)
                })
    }

    @SuppressLint("CheckResult")
    fun getFilteredFact(category:String){
        GetFilteredFactUseCaseImpl(GetFilteredFactRepositoryImpl(
            GetFilteredFactDataSourceImpl(RetrofitInstance())
        )).getFilteredFact(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Log.d("filter chuck norris", "${response}")
            },
                { throwable ->
                    Log.d("filter c. norris error", throwable.localizedMessage)
                })
    }

}