package com.example.randomdoggo.network

import com.example.randomdoggo.generate.RandomDogImage
import retrofit2.http.GET

interface RandomDoggoService {
    @GET("breeds/image/random")
    suspend fun generateRandomImage(): RandomDogImage?
}