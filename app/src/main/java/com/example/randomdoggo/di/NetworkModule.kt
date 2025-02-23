package com.example.randomdoggo.di

import com.example.randomdoggo.data.network.NetworkClient
import com.example.randomdoggo.data.network.RandomDoggoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        return NetworkClient.okHttpClient
    }

    @Singleton
    @Provides
    fun provideRandomDoggoService(client: OkHttpClient): RandomDoggoService {
        return NetworkClient.createDoggoService(client)
    }
}