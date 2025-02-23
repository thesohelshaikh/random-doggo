package com.example.randomdoggo.ui.recent

sealed class RecentScreenEvent {
    data object ClearDogs: RecentScreenEvent()
}