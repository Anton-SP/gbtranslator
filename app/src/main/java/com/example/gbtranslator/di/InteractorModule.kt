package com.example.gbtranslator.di

import com.example.gbtranslator.data.Word
import com.example.gbtranslator.repository.Repository
import com.example.gbtranslator.view.startscreen.StartScreenInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {
    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<Word>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<Word>>
    ) = StartScreenInteractor(repositoryRemote, repositoryLocal)
}