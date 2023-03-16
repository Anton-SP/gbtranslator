package com.example.featuretranslator.repository

import com.example.featuretranslator.data.AppState
import com.example.featuretranslator.data.Word
import com.example.featuretranslator.source.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<Word>>) :
    RepositoryLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}