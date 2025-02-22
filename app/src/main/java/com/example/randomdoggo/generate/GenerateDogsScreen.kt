package com.example.randomdoggo.generate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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
    viewModel: GenerateDogsViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (!state.imageUrl.isNullOrEmpty()) {
            AsyncImage(
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Image of a dog",
            )
        }

        val context = LocalContext.current
        Button(onClick = {
            viewModel.generate(context)
        }) {
            Text("Generate!")
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