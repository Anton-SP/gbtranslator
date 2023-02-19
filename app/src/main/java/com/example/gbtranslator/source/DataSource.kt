package com.example.gbtranslator.source

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}