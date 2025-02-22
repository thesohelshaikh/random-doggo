package com.example.randomdoggo.recent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.randomdoggo.R
import com.example.randomdoggo.ui.theme.RandomDoggoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentScreen(
    modifier: Modifier = Modifier,
    viewModel: RecentViewModel = viewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = "key") {
        viewModel.loadImages(context)
    }
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val carouselState = rememberCarouselState { state.images.size }

        HorizontalUncontainedCarousel(
            state = carouselState,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp),
            itemWidth = 250.dp,
            flingBehavior = CarouselDefaults.multiBrowseFlingBehavior(carouselState)
        ) { i ->
            val item = state.images[i]
            AsyncImage(
                modifier = Modifier
                    .maskClip(MaterialTheme.shapes.extraLarge)
                    .size(250.dp),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.cd_image_of_a_dog),
            )
        }

        Button(onClick = {
            viewModel.clearCache(context)
        }) {
            Text(stringResource(R.string.button_clear_dogs))
        }
    }

}

data class CarouselItem(
    val breed: String,
    val imageUrl: String,
)


@Preview
@Composable
private fun PreviewRecent() {
    RandomDoggoTheme {
        RecentScreen()
    }
}