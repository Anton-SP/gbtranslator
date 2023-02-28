package com.example.gbtranslator.source

import com.example.gbtranslator.data.Word
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    suspend fun searchAsync(@Query("search") wordToSearch: String): List<Word>
}