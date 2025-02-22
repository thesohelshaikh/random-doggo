package com.example.randomdoggo.recent

sealed class RecentScreenEvent {
    data object ClearDogs: RecentScreenEvent()
}