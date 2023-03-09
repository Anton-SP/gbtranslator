package com.example.featuretranslator.view.base

import com.example.featuretranslator.data.AppState

interface View {
    fun renderData(appState: com.example.featuretranslator.data.AppState)
}