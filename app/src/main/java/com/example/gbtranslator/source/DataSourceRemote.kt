package com.example.gbtranslator.source

import com.example.gbtranslator.data.Word
import io.reactivex.Observable

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> = remoteProvider.getData(word)
}
