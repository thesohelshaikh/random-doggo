package com.example.randomdoggo.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object NetworkClient {

    private const val BASE_URL = "https://dog.ceo/api/"
    private val contentType = "application/json".toMediaType()
    private val jsonConfig = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private var networkInterceptor: Interceptor = Interceptor { chain ->
        var request: Request = chain.request()

        request = request
            .newBuilder()
            .build()
        chain.proceed(request)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(networkInterceptor)
        .build()


    val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(jsonConfig.asConverterFactory(contentType))
        .build()
        .create(RandomDoggoService::class.java)
}