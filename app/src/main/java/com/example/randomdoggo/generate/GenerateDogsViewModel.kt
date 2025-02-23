package com.example.randomdoggo.generate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdoggo.cache.LruImageCache
import com.example.randomdoggo.network.RandomDoggoService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GenerateDogsViewModel @Inject constructor(
    private val service: RandomDoggoService,
    private val cache: LruImageCache,
) : ViewModel() {
    var uiState = MutableStateFlow(GenerateScreenState(imageUrl = null))
        private set

    private fun generate() {
        viewModelScope.launch {
           try {
               uiState.update {
                   uiState.value.copy(isLoading = true, imageUrl = null)
               }
               val image = service.generateRandomImage()
               Timber.d("Image generated:" + image?.message)
               Timber.d("Breed:" + image?.extractBreed(image.message!!))
               Timber.d("Name:" + image?.extractName(image.message!!))
               uiState.update {
                   uiState.value.copy(imageUrl = image?.message, isLoading = false)
               }
               if (image?.message != null) {
                   cache.putImageUrl(image.message)
               }
           } catch (e: Exception) {
               Timber.e(e)
               uiState.update {
                   uiState.value.copy(isError = true, isLoading = false)
               }
           }
        }
    }

    fun onEvent(event: GenerateScreenEvent) {
        when(event) {
            GenerateScreenEvent.GenerateImage -> {
                generate()
            }
        }
    }
}