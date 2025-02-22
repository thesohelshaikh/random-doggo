package com.example.randomdoggo.generate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdoggo.network.NetworkClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class GenerateDogsViewModel : ViewModel() {
    var uiState = MutableStateFlow(GenerateScreenState(imageUrl = null))
        private set

    fun generate() {
        viewModelScope.launch {
           try {
               val image = NetworkClient.service.generateRandomImage()
               Timber.d("Image generated:" + image?.message)
               uiState.update {
                   uiState.value.copy(imageUrl = image?.message)
               }
           } catch (e: Exception) {
               Timber.e(e)
           }
        }
    }
}