package com.example.gbtranslator.repository

import com.example.gbtranslator.data.Word
import com.example.gbtranslator.source.DataSource
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<Word>>) :
    Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}