package com.example.randomdoggo.recent

data class RecentScreenState(
    val images: List<CarouselItem> = listOf(),
    val isClearing: Boolean = false,
)

data class CarouselItem(
    val breed: String,
    val imageUrl: String,
)