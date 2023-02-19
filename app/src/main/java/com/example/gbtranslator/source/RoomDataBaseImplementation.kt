package com.example.gbtranslator.source

import com.example.gbtranslator.data.Word
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> {
        TODO("not implemented")
    }
}