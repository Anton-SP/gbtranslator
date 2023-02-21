package com.example.gbtranslator.view.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gbtranslator.data.AppState
import com.example.gbtranslator.presenter.Presenter
import com.example.gbtranslator.viewmodel.BaseViewModel

abstract class BaseFragment<T : AppState> : Fragment() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

}