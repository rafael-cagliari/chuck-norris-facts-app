package com.rafael.chuck_norris.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.rafael.data.datasource.AddFactToDBDataSourceImpl
import com.rafael.data.datasource.GetFactDataSourceImpl
import com.rafael.data.datasource.GetFilteredFactDataSourceImpl
import com.rafael.data.network.RetrofitInstance
import com.rafael.data.persistance.FactsDataBase
import com.rafael.data.repository.AddFactToDBRepositoryImpl
import com.rafael.data.repository.GetFactRepositoryImpl
import com.rafael.data.repository.GetFilteredFactRepositoryImpl
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.usecase.AddFactToDBUseCaseImpl
import com.rafael.domain.usecase.GetFactUseCaseImpl
import com.rafael.domain.usecase.GetFilteredFactUseCaseImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FactListViewModel(context: Context) : ViewModel() {

    var context = context

    var retrievedFact: ChuckNorrisFact = ChuckNorrisFact(
        id = "",
        categories = listOf(""),
        value = ""
    )

    @SuppressLint("CheckResult")
    fun getFact() {
        GetFactUseCaseImpl(
            GetFactRepositoryImpl(
                GetFactDataSourceImpl(RetrofitInstance())
            )
        ).getFact()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> retrievedFact=response; Log.d("chuck norris fact", "${response}")
            },
                { throwable ->
                    Log.d("chuck norris error", throwable.localizedMessage)
                })
    }

    @SuppressLint("CheckResult")
    fun getFilteredFact(category: String) {
        GetFilteredFactUseCaseImpl(
            GetFilteredFactRepositoryImpl(
                GetFilteredFactDataSourceImpl(RetrofitInstance())
            )
        ).getFilteredFact(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
              retrievedFact=response; Log.d("chuck norris fact", "${response}")},
                { throwable ->
                    Log.d("chuck norris error", throwable.localizedMessage)
                })
    }

    @SuppressLint("CheckResult")
    fun addFactToDB(fact: ChuckNorrisFact) {
        AddFactToDBUseCaseImpl(
            AddFactToDBRepositoryImpl(
                AddFactToDBDataSourceImpl(FactsDataBase.getDatabase(context).factsDao())
            )
        ).addFactToDB(fact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    Log.d("added to db c. norris", "${response}")
                },
                { throwable ->
                    Log.d("added to db c. norris", throwable.localizedMessage)
                })
    }
}
