package com.example.randomdoggo.cache

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// Extension to create DataStore
private val Context.dataStore by preferencesDataStore(name = "image_cache")

class LruImageCache(
    private val context: Context,
    private val cacheSize: Int = DEFAULT_CACHE_SIZE,
) {
    private val imageKey = stringSetPreferencesKey("cached_images")

    /**
     * Put image URL into cache
     */
    suspend fun putImageUrl(url: String) {
        context.dataStore.edit { prefs ->
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
        val prefs = context.dataStore.data.first()
        return prefs[imageKey] ?: emptySet()
    }

    /**
     * Clear all image URLs from cache
     */
    suspend fun clearCache() {
        context.dataStore.edit { prefs ->
            prefs[imageKey] = emptySet()
        }
    }

    companion object {
        private const val DEFAULT_CACHE_SIZE = 20
    }
}
