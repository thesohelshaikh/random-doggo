package com.example.randomdoggo.data.network

import com.example.randomdoggo.data.network.model.RandomDogImage
import retrofit2.http.GET

interface RandomDoggoService {
    @GET("breeds/image/random")
    suspend fun generateRandomImage(): RandomDogImage?
}