package com.example.randomdoggo.ui.generate

sealed class GenerateScreenEvent {
    data object GenerateImage: GenerateScreenEvent()
}