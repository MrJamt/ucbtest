package com.ucb.ucbtest.service
import android.Manifest
import android.R
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Util {
    companion object {
        fun sendNotification(context: Context) {
            // Check if the app has the POST_NOTIFICATIONS permission
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS,
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is granted, so show the notification
                showNotification(context = context)
            } else {
                requestPermission(context)
            }
        }

        @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
        private fun showNotification(context: Context) {
            val channelId = "default_channel"

            // Crear NotificationChannel para Android 8.0+ (API 26+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelName = "Default Channel"
                val importance = NotificationManager.IMPORTANCE_HIGH

                // Crear canal con sonido y vibración
                val channel =
                    NotificationChannel(channelId, channelName, importance).apply {
                        description = "This is a default notification channel with sound."
                        enableVibration(true)
                        enableLights(true)

                        // Establecer sonido predeterminado
                        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                        setSound(soundUri, null)
                    }

                // Crear el canal de notificación
                val notificationManager = context.getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(channel)
            }

            // Crear la notificación
            val notification =
                NotificationCompat
                    .Builder(context, channelId)
                    .setContentTitle("¡Nueva notificación!")
                    .setContentText("Esta notificación debería sonar.")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true) // Se cierra cuando el usuario la toca
                    .build()

            // Mostrar la notificación
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(1, notification)
        }

        private fun requestPermission(context: Context) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS,
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1,
                )
            }
        }
    }
}
