package com.example.schoolcomposeui

import android.app.Application
import com.example.schoolcomposeui.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(networkModule)
        }
    }
}