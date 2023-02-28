package com.example.gbtranslator.source

import com.example.gbtranslator.data.Word
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        TODO("not implemented")
    }
}