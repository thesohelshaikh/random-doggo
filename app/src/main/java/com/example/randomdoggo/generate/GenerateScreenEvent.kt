package com.example.randomdoggo.generate

sealed class GenerateScreenEvent {
    data object GenerateImage: GenerateScreenEvent()
}