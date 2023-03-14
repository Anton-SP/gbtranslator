package com.example.featuretranslator.repository

import com.example.featuretranslator.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}