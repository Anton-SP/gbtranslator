package com.example.gbtranslator.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                com.example.featuretranslator.di.featureTranslatorModule
            )
        }
    }
}