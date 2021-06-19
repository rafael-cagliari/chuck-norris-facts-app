package com.rafael.data.persistance


import android.content.Context
import androidx.room.*
import com.rafael.data.model.ChuckNorrisFactDBItem


@Database(entities = [ChuckNorrisFactDBItem::class], version = 1)
@TypeConverters()
abstract class FactsDataBase: RoomDatabase() {

    abstract fun factsDao(): FactsDao

    companion object{
        @Volatile
        private var INSTANCE: FactsDataBase? = null

        fun getDatabase(context: Context): FactsDataBase{
            val tempInstance = INSTANCE

            //guarantees instance is a singleton
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FactsDataBase::class.java,
                    "facts_table")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}