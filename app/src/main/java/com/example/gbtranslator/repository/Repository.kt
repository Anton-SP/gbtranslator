package com.example.gbtranslator.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}
