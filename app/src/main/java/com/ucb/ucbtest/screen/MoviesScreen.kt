package com.ucb.ucbtest.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MoviesScreen() {
    Scaffold(
        content = { paddingValues ->
            MoviesScreenContent(modifier = Modifier.padding(paddingValues))
        },
    )
}

@Composable
fun MoviesScreenContent(modifier: Modifier) {
    Button(
        onClick = {},
    ) {
        Text(text = "Ir al detalle")
    }
}
