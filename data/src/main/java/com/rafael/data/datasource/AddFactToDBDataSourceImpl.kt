package com.rafael.data.datasource
import android.util.Log
import com.rafael.data.model.ChuckNorrisFactDBItem
import com.rafael.data.persistance.FactsDao
import java.lang.Exception


class AddFactToDBDataSourceImpl(val factsDao: FactsDao):AddFactToDBDataSource {
    override fun addFactToDB(fact: ChuckNorrisFactDBItem){
        try {
            return factsDao.addFactToDB(fact)
        }
        catch (exception:Exception){
            Log.d("error adding to tb", exception.localizedMessage)
        }
        }
    }