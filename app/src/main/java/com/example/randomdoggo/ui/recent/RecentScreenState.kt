package com.example.randomdoggo.ui.recent

data class RecentScreenState(
    val images: List<CarouselItem> = listOf(),
)

data class CarouselItem(
    val breed: String,
    val imageUrl: String,
)