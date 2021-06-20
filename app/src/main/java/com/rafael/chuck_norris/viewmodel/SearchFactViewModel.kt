package com.rafael.chuck_norris.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.usecase.AddFactToDBUseCase
import com.rafael.domain.usecase.GetFactUseCase
import com.rafael.domain.usecase.GetFilteredFactUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchFactViewModel(
    val getFactUseCase: GetFactUseCase,
    val getFilteredFactUseCase: GetFilteredFactUseCase,
    val addFactToDbUseCase: AddFactToDBUseCase
) : ViewModel(), KoinComponent {

    val context: Context by inject()

    var _retrievedFact = MutableLiveData<ChuckNorrisFact>()
    val retrievedFact: LiveData<ChuckNorrisFact> get() = _retrievedFact

    var _category = MutableLiveData<String>("")
    val category: LiveData<String> get() = _category

    var _getFactException = MutableLiveData<String>()
    val getFactException: LiveData<String> get() = _getFactException

    var _addToDbResult = MutableLiveData<Long>()
    val addToDbResult: LiveData<Long> get() = _addToDbResult


    @SuppressLint("CheckResult")
    fun getFact() {
        getFactUseCase.getFact()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _retrievedFact.value = response
            },
                { throwable ->
                    _getFactException.value = throwable.localizedMessage
                })
    }

    @SuppressLint("CheckResult")
    fun getFilteredFact(category: String) {
        getFilteredFactUseCase.getFilteredFact(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _retrievedFact.value = response
            },
                { throwable ->
                    _getFactException.value = throwable.localizedMessage
                })
    }

    @SuppressLint("CheckResult")
    fun addFactToDB(fact: ChuckNorrisFact) {
        addFactToDbUseCase.addFactToDB(fact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    _addToDbResult.value = response
                },
                { throwable ->
                    Log.d("add to db error", throwable.localizedMessage)
                })
    }

    fun reset() {
        _category = MutableLiveData<String>("")
        _addToDbResult = MutableLiveData<Long>()
        _getFactException = MutableLiveData<String>()
        _retrievedFact = MutableLiveData<ChuckNorrisFact>()
    }
}