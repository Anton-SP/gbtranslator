package com.example.gbtranslator.view.base

import com.example.gbtranslator.data.AppState

interface View {
    fun renderData(appState: AppState)
}