package com.example.randomdoggo.ui.generate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.randomdoggo.R
import com.example.randomdoggo.ui.theme.RandomDoggoTheme

@Composable
fun GenerateDogsScreen(
    modifier: Modifier = Modifier,
    viewModel: GenerateDogsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {
        if (state.isError) {
            Text(
                text = stringResource(R.string.label_error),
                color = MaterialTheme.colorScheme.error
            )
        }
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(strokeWidth = 4.dp)
            }
            AsyncImage(
                modifier = Modifier.size(250.dp),
                model = ImageRequest.Builder(LocalContext.current).data(state.imageUrl)
                    .crossfade(true).build(),
                contentDescription = stringResource(R.string.cd_image_of_a_dog),
            )
        }

        if (state.breed != null) {
            Text(
                text = state.breed ?: "",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(16.dp))
        }

        Button(enabled = !state.isLoading, onClick = {
            viewModel.onEvent(GenerateScreenEvent.GenerateImage)
        }) {
            Text(stringResource(R.string.button_generate))
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true)
@Composable
private fun PreviewGenerateDogsScreen() {
    val previewHandler = AsyncImagePreviewHandler {
        ColorImage(Color.Red.toArgb())
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        RandomDoggoTheme {
            GenerateDogsScreen()
        }
    }
}