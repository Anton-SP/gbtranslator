package com.example.gbtranslator.source

import io.reactivex.Observable

interface DataSource<T> {
    suspend fun getData(word: String): T
}