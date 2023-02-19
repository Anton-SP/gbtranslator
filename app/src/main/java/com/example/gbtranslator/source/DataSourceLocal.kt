package com.example.gbtranslator.source

import com.example.gbtranslator.data.Word
import io.reactivex.Observable

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<Word>> {
    override fun getData(word: String): Observable<List<Word>> = remoteProvider.getData(word)
}