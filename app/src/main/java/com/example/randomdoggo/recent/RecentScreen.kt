package com.example.randomdoggo.recent

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.TargetedFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselItemInfo
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.example.randomdoggo.R
import com.example.randomdoggo.ui.theme.RandomdoggoTheme

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

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Recent")
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { state.images.size },
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            preferredItemWidth = 250.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            val item = state.images[i]
            AsyncImage(
                modifier = Modifier
                    .maskClip(MaterialTheme.shapes.extraLarge)
                    .size(250.dp)
                    .background(Color.Red)
                ,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Image of a dog",
            )
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
    RandomdoggoTheme {
        RecentScreen()
    }
}