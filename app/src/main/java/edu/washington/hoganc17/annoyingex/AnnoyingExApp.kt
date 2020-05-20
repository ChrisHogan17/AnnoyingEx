package edu.washington.hoganc17.annoyingex

import android.app.Application
import androidx.work.WorkManager
import edu.washington.hoganc17.annoyingex.manager.APIManager
import edu.washington.hoganc17.annoyingex.manager.AnnoyingExNotificationManager
import edu.washington.hoganc17.annoyingex.manager.TimedMessageManager

class AnnoyingExApp(): Application() {

    lateinit var apiManager: APIManager
        private set

    lateinit var annoyingExNotificationManager: AnnoyingExNotificationManager
        private set

    lateinit var timedMessageManager: TimedMessageManager
        private set


    override fun onCreate() {
        super.onCreate()

        apiManager = APIManager(this)
        annoyingExNotificationManager = AnnoyingExNotificationManager(this, apiManager)
        timedMessageManager = TimedMessageManager(this)
    }


}