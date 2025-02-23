package com.example.randomdoggo.generate

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdoggo.cache.LruImageCache
import com.example.randomdoggo.network.NetworkClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class GenerateDogsViewModel : ViewModel() {
    var uiState = MutableStateFlow(GenerateScreenState(imageUrl = null))
        private set

    private fun generate(context: Context) {
        viewModelScope.launch {
           try {
               uiState.update {
                   uiState.value.copy(isLoading = true, imageUrl = null)
               }
               val image = NetworkClient.service.generateRandomImage()
               Timber.d("Image generated:" + image?.message)
               Timber.d("Breed:" + image?.extractBreed(image.message!!))
               Timber.d("Name:" + image?.extractName(image.message!!))
               uiState.update {
                   uiState.value.copy(imageUrl = image?.message, isLoading = false)
               }
               if (image?.message != null) {
                   LruImageCache(context).putImageUrl(image.message)
               }
           } catch (e: Exception) {
               Timber.e(e)
               uiState.update {
                   uiState.value.copy(isError = true, isLoading = false)
               }
           }
        }
    }

    fun onEvent(event: GenerateScreenEvent, context: Context) {
        when(event) {
            GenerateScreenEvent.GenerateImage -> {
                generate(context)
            }
        }
    }
}