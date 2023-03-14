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
import com.example.featuretranslator.view.translator.TranslatorScreenInteractor
import com.example.featuretranslator.view.translator.TranslatorScreenViewModel
import org.koin.dsl.module

val source = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<Word>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<Word>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { TranslatorScreenViewModel(get()) }
    factory { TranslatorScreenInteractor(get(),get()) }
}

val featureTranslatorModule = module {
    includes(source, mainScreen)
}