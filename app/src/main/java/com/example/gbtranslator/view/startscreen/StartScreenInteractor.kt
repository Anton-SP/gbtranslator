package com.example.gbtranslator.view.startscreen

import com.example.gbtranslator.data.AppState
import com.example.gbtranslator.data.Word
import com.example.gbtranslator.di.NAME_LOCAL
import com.example.gbtranslator.di.NAME_REMOTE
import com.example.gbtranslator.presenter.Interactor
import com.example.gbtranslator.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class StartScreenInteractor @Inject constructor(
    @Named(NAME_REMOTE) private val remoteRepository: Repository<List<Word>>,
    @Named(NAME_LOCAL) private val localRepository: Repository<List<Word>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
