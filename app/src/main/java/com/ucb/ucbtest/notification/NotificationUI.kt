// NotificacionUI.kt
package com.ucb.ucbtest.notification

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ucb.ucbtest.service.Util

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificacionUI() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notificaciones") },
            )
        },
    ) { padding ->
        IconButton(
            onClick = {
                Util.sendNotification(context)
            },
            modifier =
                androidx.compose.ui.Modifier
                    .padding(padding),
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notificar",
            )
        }
    }
}
