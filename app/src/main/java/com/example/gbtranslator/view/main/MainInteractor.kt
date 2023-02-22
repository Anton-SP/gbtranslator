package com.example.gbtranslator.view.main

import com.example.gbtranslator.data.AppState
import com.example.gbtranslator.data.Word
import com.example.gbtranslator.presenter.Interactor
import com.example.gbtranslator.repository.Repository
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<Word>>,
    private val localRepository: Repository<List<Word>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
