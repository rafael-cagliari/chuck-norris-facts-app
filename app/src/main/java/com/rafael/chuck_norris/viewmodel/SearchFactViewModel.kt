package com.rafael.chuck_norris.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafael.data.datasource.DataBaseDataSourceImpl
import com.rafael.data.datasource.GetFactDataSourceImpl
import com.rafael.data.network.RetrofitInstance
import com.rafael.data.persistance.FactsDataBase
import com.rafael.data.repository.DataBaseRepositoryImpl
import com.rafael.data.repository.GetFactRepositoryImpl
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.usecase.AddFactToDBUseCaseImpl
import com.rafael.domain.usecase.GetFactUseCaseImpl
import com.rafael.domain.usecase.GetFilteredFactUseCaseImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchFactViewModel():ViewModel(), KoinComponent {

   val context:Context by inject()


    val _category = MutableLiveData<String>()
    val category: LiveData<String> get() = _category

    var retrievedFact =  MutableLiveData<ChuckNorrisFact>()


    @SuppressLint("CheckResult")
    fun getFact() {
        GetFactUseCaseImpl(
            GetFactRepositoryImpl(
                GetFactDataSourceImpl(RetrofitInstance())
            )
        ).getFact()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> retrievedFact.value=response; Log.d("chuck norris fact", "${response}")
            },
                { throwable ->
                    Log.d("chuck norris error", throwable.localizedMessage)
                })
    }

    @SuppressLint("CheckResult")
    fun getFilteredFact(category: String) {
        GetFilteredFactUseCaseImpl(
            GetFactRepositoryImpl(
                GetFactDataSourceImpl(RetrofitInstance())
            )
        ).getFilteredFact(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                retrievedFact.value=response; Log.d("chuck norris fact", "${response}")},
                { throwable ->
                    Log.d("chuck norris error", throwable.localizedMessage)
                })
    }

    @SuppressLint("CheckResult")
    fun addFactToDB(fact: ChuckNorrisFact) {
        AddFactToDBUseCaseImpl(
            DataBaseRepositoryImpl(
                DataBaseDataSourceImpl(FactsDataBase.getDatabase(context).factsDao())
            )
        ).addFactToDB(fact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    Log.d("added to db c. norris", "${response}")
                },
                { throwable ->
                    Log.d("chuck norris error", throwable.localizedMessage)
                })
    }

}