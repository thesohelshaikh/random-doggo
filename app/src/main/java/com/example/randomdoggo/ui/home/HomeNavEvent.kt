package com.example.randomdoggo.ui.theme.home

sealed class HomeNavEvent {
    data object NavigateToGenerate: HomeNavEvent()
    data object NavigateToGenerated: HomeNavEvent()
}