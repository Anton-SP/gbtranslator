package com.example.gbtranslator.source

import com.example.gbtranslator.data.Word
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<Word>>
}