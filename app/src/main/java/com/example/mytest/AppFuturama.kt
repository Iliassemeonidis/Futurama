package com.example.mytest

import android.app.Application
import com.example.mytest.di.application
import com.example.mytest.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppFuturama : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(application,mainScreen)
        }
    }
}