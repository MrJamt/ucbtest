package com.ucb.ucbtest.screen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieDetailScreen() {
    Scaffold(
        content = { paddingValues ->
            MovieDetailScreenContent(modifier = Modifier.padding(paddingValues))
        },
    )
}

@Composable
fun MovieDetailScreenContent(modifier: Modifier) {
    Text(text = "Pantalla del detalle")
}
