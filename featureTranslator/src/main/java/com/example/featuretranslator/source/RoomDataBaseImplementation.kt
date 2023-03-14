package com.example.featuretranslator.source

import com.example.featuretranslator.data.AppState
import com.example.featuretranslator.data.Word
import com.example.featuretranslator.room.HistoryDao
import com.example.featuretranslator.utils.ui.convertDataModelSuccessToEntity
import com.example.featuretranslator.utils.ui.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}