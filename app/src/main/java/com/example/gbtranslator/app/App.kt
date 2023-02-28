package com.example.gbtranslator.app

import android.app.Application
import com.example.gbtranslator.di.application
import com.example.gbtranslator.di.mainScreen
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}