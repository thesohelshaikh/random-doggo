package com.example.randomdoggo.generate


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomDogImage(
    @SerialName("message")
    val message: String?,
    @SerialName("status")
    val status: String
)