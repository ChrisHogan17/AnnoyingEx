package edu.washington.hoganc17.annoyingex.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import edu.washington.hoganc17.annoyingex.model.MessagesFetchListener
import edu.washington.hoganc17.annoyingex.R
import edu.washington.hoganc17.annoyingex.activity.MessageActivity
import kotlin.random.Random

class AnnoyingExNotificationManager(
    private val context: Context,
    private val apiManager: APIManager
) {
    private val notificationManagerCompat = NotificationManagerCompat.from(context)
    var messagesFetchListener: MessagesFetchListener? = null

    private lateinit var messages: List<String>

    init {
        fetchMessages()
        createMessageChannel()
    }

    fun sendRandomMessageNotification() {
        val index = Random.nextInt(0, messages.size - 1)

        val messageIntent = Intent(context, MessageActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(MESSAGE_KEY, messages[index])
        }

        val pendingMessageIntent = PendingIntent.getActivity(
            context,
            0,
            messageIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, MESSAGE_CHANNEL_ID)
            .setSmallIcon(R.drawable.message_icon)
            .setContentTitle("Blocked Number")
            .setContentText(messages[index])
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingMessageIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(index, notification)
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

    private fun fetchMessages(){
        apiManager.fetchMessages(
            { messages ->
                messagesFetchListener?.onMessagesFetched(messages)
                this.messages = messages
            }, {
                messagesFetchListener?.onFetchError()
            }
        )
    }

    companion object {
        const val MESSAGE_CHANNEL_ID = "MESSAGE_CHANNEL_ID"
        const val MESSAGE_KEY = "MESSAGE_KEY"
    }
}