package com.rafael.data.datasource
import android.util.Log
import com.rafael.data.model.ChuckNorrisFactDBItem
import com.rafael.data.persistance.FactsDao
import io.reactivex.Single


class AddFactToDBDataSourceImpl(val factsDao: FactsDao):AddFactToDBDataSource {
    override fun addFactToDB(fact: ChuckNorrisFactDBItem): Single<Long> {
        try {
            return factsDao.addFactToDB(fact)
        }
        catch (exception:Exception){
            Log.d("error adding to tb", exception.localizedMessage)
        }
       return factsDao.addFactToDB(fact) }
    }