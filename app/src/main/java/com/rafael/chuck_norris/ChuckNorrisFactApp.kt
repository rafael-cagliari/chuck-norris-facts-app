package com.rafael.chuck_norris

import android.app.Application
import com.rafael.data.dataModule
import com.rafael.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class ChuckNorrisFactApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ChuckNorrisFactApp)
            modules(listOf(dataModule, domainModule, appModule))
        }

    }
}