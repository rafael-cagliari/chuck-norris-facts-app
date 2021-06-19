package com.rafael.data.datasource
import android.util.Log
import com.rafael.data.model.ChuckNorrisFactDBItem
import com.rafael.data.persistance.FactsDao
import com.rafael.domain.model.ChuckNorrisFact
import io.reactivex.Single


class DataBaseDataSourceImpl(val factsDao: FactsDao):DataBaseDataSource {
    override fun addFactToDB(fact: ChuckNorrisFactDBItem): Single<Long> {
        try {
            return factsDao.addFactToDB(fact)
        }
        catch (exception:Exception){
            Log.d("error adding to db", exception.localizedMessage)
        }
       return factsDao.addFactToDB(fact) }


    override fun deleteFactFromDB(id: String): Single<Int> {
        try {
            return factsDao.deleteFactFromDB(id = id)
        }
        catch (exception:Exception){
            Log.d("error deleting from db", exception.localizedMessage)
        }
        return factsDao.deleteFactFromDB(id = id)
}

    override fun readAllDB(): Single<List<ChuckNorrisFact>> {
        val database:Single<List<ChuckNorrisFactDBItem>> = factsDao.readAllDB()


     fun fromDBtoDomainConverter(result:Single<List<ChuckNorrisFactDBItem>>):Single<List<ChuckNorrisFact>>{
         return result.map{ list ->
             list.mapTo(destination = ArrayList()) {
                 ChuckNorrisFact(
                     id = it.id,
                     value = it.value,
                     categories = listOf(it.categories))
             }
         }
         }
        return fromDBtoDomainConverter(database)
     }

    }
