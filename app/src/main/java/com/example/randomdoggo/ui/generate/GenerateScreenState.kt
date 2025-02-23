package com.example.randomdoggo.ui.generate

data class GenerateScreenState(
    val imageUrl: String? = null,
    val breed: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)