package com.rafael.data

import androidx.room.Room
import com.rafael.data.datasource.DataBaseDataSource
import com.rafael.data.datasource.DataBaseDataSourceImpl
import com.rafael.data.datasource.GetFactDataSource
import com.rafael.data.datasource.GetFactDataSourceImpl
import com.rafael.data.network.RetrofitInstance
import com.rafael.data.persistance.FactsDataBase
import com.rafael.data.repository.DataBaseRepositoryImpl
import com.rafael.data.repository.GetFactRepositoryImpl
import com.rafael.domain.repository.DataBaseRepository
import com.rafael.domain.repository.GetFactRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    factory {
        RetrofitInstance().service
    }

    factory<GetFactDataSource> { GetFactDataSourceImpl(get()) }

    factory<GetFactRepository> { GetFactRepositoryImpl(get()) }


    factory<DataBaseRepository> {DataBaseRepositoryImpl(get())}

    factory<DataBaseDataSource> {DataBaseDataSourceImpl(get())}


    single(named("room_database")) {
        Room.databaseBuilder(get(), FactsDataBase::class.java, "facts_table").build()
    }
    single { get<FactsDataBase>(named("room_database")).factsDao() }

}
