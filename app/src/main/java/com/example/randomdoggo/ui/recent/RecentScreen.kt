package com.example.randomdoggo.ui.recent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.memory.MemoryCache
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.randomdoggo.R
import com.example.randomdoggo.ui.theme.RandomDoggoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentScreen(
    modifier: Modifier = Modifier,
    viewModel: RecentViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val carouselState = rememberCarouselState { state.images.size }

        Text("Recently Generated Dogs(${state.images.size})")

        if (state.images.isNotEmpty()) {
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
                Box{
                    AsyncImage(
                        modifier = Modifier
                            .maskClip(MaterialTheme.shapes.extraLarge)
                            .size(250.dp),
                        placeholder = painterResource(R.drawable.ic_launcher_background),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = stringResource(R.string.cd_image_of_a_dog),
                    )
                    Text(
                        modifier = Modifier.padding(8.dp).fillMaxWidth().align(Alignment.BottomCenter),
                        text = item.breed,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.onPrimary,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }

        Button(
            onClick = {
                viewModel.onEvent(RecentScreenEvent.ClearDogs)
            },
            enabled = state.images.isNotEmpty()
        ) {
            Text(stringResource(R.string.button_clear_dogs))
        }
    }

}


@Preview
@Composable
private fun PreviewRecent() {
    RandomDoggoTheme {
        RecentScreen()
    }
}