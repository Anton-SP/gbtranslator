package com.example.gbtranslator.di

import com.example.gbtranslator.data.Word
import com.example.gbtranslator.repository.Repository
import com.example.gbtranslator.repository.RepositoryImplementation
import com.example.gbtranslator.source.RetrofitImplementation
import com.example.gbtranslator.source.RoomDataBaseImplementation
import com.example.gbtranslator.view.startscreen.StartScreenInteractor
import com.example.gbtranslator.view.startscreen.StartScreenViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<Word>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<Word>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { StartScreenInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { StartScreenViewModel(get()) }
}