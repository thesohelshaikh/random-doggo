package com.example.randomdoggo.recent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.randomdoggo.ui.theme.RandomdoggoTheme

@Composable
fun RecentScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Recent")
    }
}

@Preview
@Composable
private fun PreviewRecent() {
    RandomdoggoTheme {
        RecentScreen()
    }
}