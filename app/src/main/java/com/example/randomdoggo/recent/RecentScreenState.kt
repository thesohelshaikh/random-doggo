package com.example.randomdoggo.recent

data class RecentScreenState(
    val images: List<String> = listOf(),
    val isClearing: Boolean = false,
)