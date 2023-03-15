package com.example.featuretranslator.repository

import com.example.featuretranslator.data.Word
import com.example.featuretranslator.source.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<Word>>) :
    Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}