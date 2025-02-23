package com.example.randomdoggo.generate

data class GenerateScreenState(
    val imageUrl: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)