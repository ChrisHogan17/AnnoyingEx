package edu.washington.hoganc17.annoyingex.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import edu.washington.hoganc17.annoyingex.R

class AnnoyingExNotificationManager(
    private val context: Context
) {
    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createMessageChannel()
    }

    fun startNotifications() {
        val notification = NotificationCompat.Builder(context, MESSAGE_CHANNEL_ID)
            .setSmallIcon(R.drawable.message_icon)
            .setContentTitle("Blocked Number")
            .setContentText("What's up?")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManagerCompat.notify(10, notification)
    }

    private fun createMessageChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Messages"
            val descriptionText = "Messages from your ex"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(MESSAGE_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    companion object {
        const val MESSAGE_CHANNEL_ID = "MESSAGE_CHANNEL_ID"
    }
}