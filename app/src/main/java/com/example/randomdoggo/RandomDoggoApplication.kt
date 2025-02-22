package com.example.randomdoggo

import android.app.Application
import timber.log.Timber

class RandomDoggoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}