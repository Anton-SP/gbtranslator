package com.example.gbtranslator.view.startscreen

import androidx.lifecycle.LiveData
import com.example.gbtranslator.data.AppState
import com.example.gbtranslator.repository.RepositoryImplementation
import com.example.gbtranslator.source.DataSourceLocal
import com.example.gbtranslator.source.DataSourceRemote
import com.example.gbtranslator.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableObserver

class StartScreenViewModel (
    private val interactor: StartScreenInteractor = StartScreenInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    )
) : BaseViewModel<AppState>(){

    private var appState: AppState? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(state: AppState) {
                appState = state
                liveDataForViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }


}