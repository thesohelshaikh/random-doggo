package com.example.randomdoggo.ui.theme.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.randomdoggo.R
import com.example.randomdoggo.ui.theme.RandomDoggoTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onEvent: (HomeNavEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            onEvent(HomeNavEvent.NavigateToGenerate)
        }) {
            Text(stringResource(R.string.label_generate_dogs))
        }
        Button(onClick = {
            onEvent(HomeNavEvent.NavigateToGenerated)
        }) {
            Text(stringResource(R.string.label_recently_generated))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    RandomDoggoTheme {
        HomeScreen(onEvent = {})
    }
}