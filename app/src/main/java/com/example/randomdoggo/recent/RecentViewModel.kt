package com.example.randomdoggo.recent

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdoggo.cache.LruImageCache
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class RecentViewModel: ViewModel() {

    val uiState = MutableStateFlow(RecentScreenState())

    fun loadImages(context: Context) {
        viewModelScope.launch {
            try {
                val images = LruImageCache(context).getCachedUrls()
                Timber.d("Images (${images.size}): $images")

                uiState.value = uiState.value.copy(images = images.toList().reversed())

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun onEvent(event: RecentScreenEvent, context: Context) {
        when (event) {
            is RecentScreenEvent.ClearDogs -> {
                clearCache(context)
            }
        }
    }

    private fun clearCache(context: Context) {
        viewModelScope.launch {
            uiState.update {
                uiState.value.copy(isClearing = true)
            }
            val cache = LruImageCache(context)
            cache.clearCache()
            loadImages(context)
            uiState.update {
                uiState.value.copy(isClearing = false)
            }
        }
    }
}