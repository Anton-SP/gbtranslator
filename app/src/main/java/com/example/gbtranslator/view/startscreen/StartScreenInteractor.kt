package com.example.gbtranslator.view.startscreen

import com.example.gbtranslator.data.AppState
import com.example.gbtranslator.data.Word
import com.example.gbtranslator.repository.Repository
import com.example.gbtranslator.viewmodel.Interactor

class StartScreenInteractor (
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