package com.example.randomdoggo.recent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdoggo.cache.LruImageCache
import com.example.randomdoggo.generate.RandomDogImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    private val cache: LruImageCache
) : ViewModel() {

    val uiState = MutableStateFlow(RecentScreenState())

    fun loadImages() {
        viewModelScope.launch {
            try {
                val images = cache.getCachedUrls()
                Timber.d("Images (${images.size}): $images")

                uiState.value = uiState.value.copy(
                    images = images.map {
                        CarouselItem(
                            breed = RandomDogImage.extractBreed(it) ?: "",
                            imageUrl = it
                        )
                    }.reversed()
                )

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun onEvent(event: RecentScreenEvent) {
        when (event) {
            is RecentScreenEvent.ClearDogs -> {
                clearCache()
            }
        }
    }

    private fun clearCache() {
        viewModelScope.launch {
            uiState.update {
                uiState.value.copy(isClearing = true)
            }
            cache.clearCache()
            loadImages()
            uiState.update {
                uiState.value.copy(isClearing = false)
            }
        }
    }
}