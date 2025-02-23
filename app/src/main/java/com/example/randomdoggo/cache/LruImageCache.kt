package com.example.randomdoggo.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LruImageCache @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val cacheSize: Int = DEFAULT_CACHE_SIZE,
) {
    private val imageKey = stringSetPreferencesKey("cached_images")

    /**
     * Put image URL into cache
     */
    suspend fun putImageUrl(url: String) {
        dataStore.edit { prefs ->
            val urls = prefs[imageKey]?.toMutableSet() ?: mutableSetOf()
            if (urls.size >= cacheSize) urls.remove(urls.first()) // Remove oldest
            urls.add(url)
            prefs[imageKey] = urls
        }
    }

    /**
     * Get all image URLs from cache
     */
    suspend fun getCachedUrls(): Set<String> {
        val prefs = dataStore.data.first()
        return prefs[imageKey] ?: emptySet()
    }

    /**
     * Clear all image URLs from cache
     */
    suspend fun clearCache() {
        dataStore.edit { prefs ->
            prefs[imageKey] = emptySet()
        }
    }

    companion object {
        private const val DEFAULT_CACHE_SIZE = 20
    }
}
