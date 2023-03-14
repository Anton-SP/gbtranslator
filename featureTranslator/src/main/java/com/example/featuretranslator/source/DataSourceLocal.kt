package com.example.featuretranslator.source

import com.example.featuretranslator.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}