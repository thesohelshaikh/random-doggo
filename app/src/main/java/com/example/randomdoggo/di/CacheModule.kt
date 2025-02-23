package com.example.randomdoggo.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.randomdoggo.cache.LruImageCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "image_cache"
    )

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext app: Context): DataStore<Preferences> {
        return app.dataStore
    }

    @Provides
    @Singleton
    fun provideLruImageCache(dataStore: DataStore<Preferences>): LruImageCache {
        return LruImageCache(dataStore)
    }
}