package com.example.gbtranslator.app

import android.app.Application
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                com.example.featuretranslator.di.featureTranslatorModule
            )
        }
    }

   /* override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(
                com.example.featuretranslator.di.application,
                com.example.featuretranslator.di.mainScreen
            ))
        }
    }*/
}