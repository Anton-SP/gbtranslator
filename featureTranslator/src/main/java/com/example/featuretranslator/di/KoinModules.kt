package com.example.featuretranslator.di

import androidx.room.Room
import com.example.featuretranslator.data.Word
import com.example.featuretranslator.repository.Repository
import com.example.featuretranslator.repository.RepositoryImplementation
import com.example.featuretranslator.repository.RepositoryImplementationLocal
import com.example.featuretranslator.repository.RepositoryLocal
import com.example.featuretranslator.room.HistoryDataBase
import com.example.featuretranslator.source.RetrofitImplementation
import com.example.featuretranslator.source.RoomDataBaseImplementation
import com.example.featuretranslator.view.history.HistoryInteractor
import com.example.featuretranslator.view.history.HistoryScreenFragment
import com.example.featuretranslator.view.history.HistoryViewModel
import com.example.featuretranslator.view.translator.TranslatorScreenFragment
import com.example.featuretranslator.view.translator.TranslatorScreenInteractor
import com.example.featuretranslator.view.translator.TranslatorScreenViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val source = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<Word>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<Word>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    scope(named<TranslatorScreenFragment>()) {
        scoped { TranslatorScreenViewModel(get()) }
        scoped { TranslatorScreenInteractor(get(), get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryScreenFragment>()) {
        factory { HistoryViewModel(get()) }
        factory { HistoryInteractor(get(), get()) }
    }

}

val featureTranslatorModule = module {
    includes(source, mainScreen, historyScreen)
}