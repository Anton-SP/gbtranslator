package com.example.featuretranslator.view.translator

import com.example.featuretranslator.data.AppState
import com.example.featuretranslator.data.Word
import com.example.featuretranslator.repository.Repository
import com.example.featuretranslator.viewmodel.Interactor

class TranslatorScreenInteractor (
    private val repositoryRemote: Repository<List<Word>>,
    private val repositoryLocal: Repository<List<Word>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}