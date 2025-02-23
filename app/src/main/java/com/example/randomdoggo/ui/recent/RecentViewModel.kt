package com.example.randomdoggo.ui.recent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdoggo.data.local.LruImageCache
import com.example.randomdoggo.data.network.model.RandomDogImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    private val cache: LruImageCache
) : ViewModel() {

    val uiState: StateFlow<RecentScreenState> = cache.getCachedUrls().map {
        RecentScreenState(
            images = it.map { url ->
                CarouselItem(
                    breed = RandomDogImage.extractBreed(url) ?: "",
                    imageUrl = url
                )
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RecentScreenState(),
    )

    fun onEvent(event: RecentScreenEvent) {
        when (event) {
            is RecentScreenEvent.ClearDogs -> {
                clearCache()
            }
        }
    }

    private fun clearCache() {
        viewModelScope.launch {
            cache.clearCache()
        }
    }
}