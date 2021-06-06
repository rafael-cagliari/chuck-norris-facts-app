package com.rafael.chuck_norris

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rafael.data.datasource.RandomFactRequestDataSourceImpl
import com.rafael.data.datasource.RandomFilteredFactRequestDataSourceImpl
import com.rafael.data.network.RetrofitInstance
import com.rafael.data.repository.ChuckNorrisFactRequestRepositoryImpl
import com.rafael.domain.usecase.RandomFactRequestImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestRandomFact()
    }

    fun requestRandomFact() {
        RandomFactRequestImpl(
            ChuckNorrisFactRequestRepositoryImpl(
                RandomFactRequestDataSourceImpl(RetrofitInstance()),
                RandomFilteredFactRequestDataSourceImpl(RetrofitInstance())
            )
        ).getRandomFact()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Log.d("result chuck norris", "${response}")
            },
                { throwable ->
                    Log.d("chuck norris error", "${throwable.localizedMessage}")
                })
    }

}