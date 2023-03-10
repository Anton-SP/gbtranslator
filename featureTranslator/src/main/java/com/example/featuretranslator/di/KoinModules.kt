package com.example.featuretranslator.di

import com.example.featuretranslator.repository.Repository
import com.example.featuretranslator.repository.RepositoryImplementation
import com.example.featuretranslator.source.RetrofitImplementation
import com.example.featuretranslator.source.RoomDataBaseImplementation
import com.example.featuretranslator.view.translator.TranslatorScreenInteractor
import com.example.featuretranslator.view.translator.TranslatorScreenViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<com.example.featuretranslator.data.Word>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<com.example.featuretranslator.data.Word>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { TranslatorScreenInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { TranslatorScreenViewModel(get()) }
}

val featureTranslatorModule = module {
    includes(application, mainScreen)
}