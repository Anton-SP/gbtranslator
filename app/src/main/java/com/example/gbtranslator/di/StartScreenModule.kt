package com.example.gbtranslator.di

import com.example.gbtranslator.view.startscreen.StartScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StartScreenModule {

    @ContributesAndroidInjector
    abstract fun contributeStartScreenFragment(): StartScreenFragment
}