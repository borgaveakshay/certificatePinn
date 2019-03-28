package com.example.myapplication.presentation_layer.utils

import android.app.Application
import com.example.myapplication.presentation_layer.dependencies.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        val appModule = AppModule(this)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(appModule.getAppModule()))

        }
        INSTANCE = this
    }
}