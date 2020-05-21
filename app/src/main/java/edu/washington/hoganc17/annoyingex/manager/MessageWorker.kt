package edu.washington.hoganc17.annoyingex.manager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import edu.washington.hoganc17.annoyingex.AnnoyingExApp

class MessageWorker(private val context: Context, workParams: WorkerParameters): Worker(context , workParams) {


    override fun doWork(): Result {
        val annoyingExNotificationManager = (context.applicationContext as AnnoyingExApp).annoyingExNotificationManager
        annoyingExNotificationManager.sendRandomMessageNotification()
        return Result.success()
    }
}