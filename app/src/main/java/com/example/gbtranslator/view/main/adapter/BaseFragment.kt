package com.example.gbtranslator.view.main.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gbtranslator.data.AppState
import com.example.gbtranslator.presenter.Presenter
import com.example.gbtranslator.view.base.View

abstract class BaseFragment<T: AppState> : Fragment(),View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}