package com.example.featuretranslator.source

import com.example.featuretranslator.data.Word
class RoomDataBaseImplementation :
    DataSource<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        TODO("not implemented")
    }
}