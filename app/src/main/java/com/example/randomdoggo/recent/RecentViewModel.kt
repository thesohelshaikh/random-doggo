package com.example.randomdoggo.recent

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdoggo.cache.LruImageCache
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class RecentViewModel: ViewModel() {

    val uiState = MutableStateFlow(RecentScreenState(images = listOf()))

    fun loadImages(context: Context) {
        viewModelScope.launch {
            try {
                val images = LruImageCache(context).getCachedUrls()
                Timber.d("Images (${images.size}): $images")

                uiState.value = uiState.value.copy(images = images.toList())

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun clearCache(context: Context) {
        viewModelScope.launch {
            val cache = LruImageCache(context)
            cache.clearCache()
            loadImages(context)
        }
    }
}