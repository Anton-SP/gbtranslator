package com.example.featuretranslator.repository

import com.example.featuretranslator.data.Word
import com.example.featuretranslator.source.DataSource

class RepositoryImplementation(private val dataSource: com.example.featuretranslator.source.DataSource<List<com.example.featuretranslator.data.Word>>) :
    com.example.featuretranslator.repository.Repository<List<com.example.featuretranslator.data.Word>> {

    override suspend fun getData(word: String): List<com.example.featuretranslator.data.Word> {
        return dataSource.getData(word)
    }
}