package com.example.gbtranslator.di

import android.app.Application
import com.example.gbtranslator.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        StartScreenModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(englishVocabularyApp: App)
}