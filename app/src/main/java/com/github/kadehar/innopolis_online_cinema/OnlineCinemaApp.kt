package com.github.kadehar.innopolis_online_cinema

import android.app.Application
import com.github.kadehar.innopolis_online_cinema.base.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OnlineCinemaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@OnlineCinemaApp)
            modules(appModule)
        }
    }
}