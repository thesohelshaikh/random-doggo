package com.example.randomdoggo.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * A Simple implementation of a LRU cache for image URLs. Only the URLs are cached on to Disk.
 * Actual image caching is done by Coil.
 *
 * @param dataStore Instance of [DataStore] to use for caching
 * @param cacheSize Maximum number of images to cache
 */
class LruImageCache @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val cacheSize: Int = DEFAULT_CACHE_SIZE,
) {
    private val imageKey = stringSetPreferencesKey("cached_images")

    /**
     * Saves an image URL into cache
     *
     * @param url Image URL to save
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
     *
     * @return Flow of [Set] of cached image URLs
     */
    fun getCachedUrls(): Flow<Set<String>> {
        return dataStore.data.map {
            it[imageKey] ?: emptySet()
        }
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
