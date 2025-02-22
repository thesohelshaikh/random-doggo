package com.example.randomdoggo.generate

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
fun GenerateDogsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Generate Dogs!")
        Button(onClick = {}) {
            Text("Generate!")
        }
    }
}

@Preview
@Composable
private fun PreviewGenerateDogsScreen() {
    RandomdoggoTheme {
        GenerateDogsScreen()
    }
}