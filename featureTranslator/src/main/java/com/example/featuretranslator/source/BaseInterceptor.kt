package com.example.featuretranslator.source

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class BaseInterceptor private constructor() : Interceptor {

    private var responseCode: Int = 0

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        responseCode = response.code()
        return response
    }

    fun getResponseCode(): com.example.featuretranslator.source.BaseInterceptor.ServerResponseStatusCode {
        var statusCode =
            com.example.featuretranslator.source.BaseInterceptor.ServerResponseStatusCode.UNDEFINED_ERROR
        when (responseCode / 100) {
            1 -> statusCode =
                com.example.featuretranslator.source.BaseInterceptor.ServerResponseStatusCode.INFO
            2 -> statusCode =
                com.example.featuretranslator.source.BaseInterceptor.ServerResponseStatusCode.SUCCESS
            3 -> statusCode =
                com.example.featuretranslator.source.BaseInterceptor.ServerResponseStatusCode.REDIRECTION
            4 -> statusCode =
                com.example.featuretranslator.source.BaseInterceptor.ServerResponseStatusCode.CLIENT_ERROR
            5 -> statusCode =
                com.example.featuretranslator.source.BaseInterceptor.ServerResponseStatusCode.SERVER_ERROR
        }
        return statusCode
    }


    enum class ServerResponseStatusCode {
        INFO,
        SUCCESS,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR,
        UNDEFINED_ERROR
    }

    companion object {

        val interceptor: com.example.featuretranslator.source.BaseInterceptor
            get() = com.example.featuretranslator.source.BaseInterceptor()
    }
}
