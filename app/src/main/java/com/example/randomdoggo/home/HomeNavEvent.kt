package com.example.randomdoggo.home

sealed class HomeNavEvent {
    data object NavigateToGenerate: HomeNavEvent()
    data object NavigateToGenerated: HomeNavEvent()
}