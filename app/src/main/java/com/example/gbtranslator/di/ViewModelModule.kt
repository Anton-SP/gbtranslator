package com.example.gbtranslator.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gbtranslator.view.startscreen.StartScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(StartScreenViewModel::class)
    protected abstract fun startScreenViewModel(startScreenViewModel: StartScreenViewModel): ViewModel
}