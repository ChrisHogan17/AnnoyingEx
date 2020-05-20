package edu.washington.hoganc17.annoyingex

import android.app.Application
import edu.washington.hoganc17.annoyingex.manager.AnnoyingExNotificationManager

class AnnoyingExApp(): Application() {

    lateinit var annoyingExNotificationManager: AnnoyingExNotificationManager
        private set

    override fun onCreate() {
        super.onCreate()

        annoyingExNotificationManager = AnnoyingExNotificationManager(this)
    }


}