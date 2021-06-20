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
import com.rafael.domain.usecase.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class FactListViewModel(val deleteFactFromDBUseCase:DeleteFactFromDBUseCase,
                        val readAllDBUseCase:ReadAllDBUseCase):ViewModel(), KoinComponent {

    val context:Context by inject()
    var readAllData : MutableLiveData<List<ChuckNorrisFact>> = MutableLiveData<List<ChuckNorrisFact>>()


    @SuppressLint("CheckResult")
    fun deleteFactFromDB(id:String){
        deleteFactFromDBUseCase.deleteFactFromDB(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    Log.d("added to db c. norris", "$response")
                },
                { throwable ->
                    Log.d("chuck norris error", throwable.localizedMessage)
                }
            )
    }

    @SuppressLint("CheckResult")
    fun readAllDB(){
       readAllDBUseCase.readAllDB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {response ->
                    readAllData.value=response
                    Log.d("read DB", "$response")
                },
                { throwable ->
                    Log.d("error retrieving DB", throwable.localizedMessage)
                }
            )
    }
}
