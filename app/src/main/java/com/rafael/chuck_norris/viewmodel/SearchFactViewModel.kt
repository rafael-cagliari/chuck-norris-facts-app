package com.rafael.chuck_norris.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import java.lang.Exception

class SearchFactViewModel():ViewModel(), KoinComponent {

   val context:Context by inject()


    var _category = MutableLiveData<String>()
    val category: LiveData<String> get() = _category

    var _getFactException = MutableLiveData<String>()
    val getFactException: LiveData<String> get() = _getFactException

    var _addToDbResult = MutableLiveData<Long>()
    val addToDbResult: LiveData<Long> get() = _addToDbResult

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
            .subscribe({ response -> retrievedFact.value=response
            },
                { throwable ->
                    _getFactException.value=throwable.localizedMessage
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
                retrievedFact.value=response},
                { throwable ->
                    _getFactException.value=throwable.localizedMessage
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
                    _addToDbResult.value=response
                },
                { throwable ->
                    Log.d("add to db error", throwable.localizedMessage)
                })
    }

    fun reset(){
        _category= MutableLiveData<String>()
        _addToDbResult=MutableLiveData<Long>()
        _getFactException=MutableLiveData<String>()
        retrievedFact=MutableLiveData<ChuckNorrisFact>()
    }
}