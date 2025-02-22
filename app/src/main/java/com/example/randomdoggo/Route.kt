package com.example.randomdoggo

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Home : Route()

    @Serializable
    data object Generate : Route()

    @Serializable
    data object Recent : Route()
}