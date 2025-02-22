package com.example.randomdoggo.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.randomdoggo.ui.theme.RandomdoggoTheme

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
            Text("Generate Dogs!")
        }
        Button(onClick = {
            onEvent(HomeNavEvent.NavigateToGenerated)
        }) {
            Text("My Recently Generated Dogs!")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    RandomdoggoTheme {
        HomeScreen(onEvent = {})
    }
}