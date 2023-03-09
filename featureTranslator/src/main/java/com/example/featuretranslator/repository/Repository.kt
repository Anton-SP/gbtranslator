package com.example.featuretranslator.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}
