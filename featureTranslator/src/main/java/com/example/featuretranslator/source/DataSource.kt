package com.example.featuretranslator.source



interface DataSource<T> {
    suspend fun getData(word: String): T
}