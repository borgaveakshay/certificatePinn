package com.example.myapplication.utils

import android.app.Application
import com.example.myapplication.dependencies.AppModule
import org.koin.core.context.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        val appModule = AppModule(this)
        startKoin { appModule.getAppModule() }
    }
}