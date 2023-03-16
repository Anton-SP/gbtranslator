package com.example.featuretranslator.view.translator

import com.example.featuretranslator.data.AppState
import com.example.featuretranslator.data.Word
import com.example.featuretranslator.repository.Repository
import com.example.featuretranslator.repository.RepositoryLocal
import com.example.featuretranslator.viewmodel.Interactor

class TranslatorScreenInteractor (
    private val repositoryRemote: Repository<List<Word>>,
    private val repositoryLocal: RepositoryLocal<List<Word>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}