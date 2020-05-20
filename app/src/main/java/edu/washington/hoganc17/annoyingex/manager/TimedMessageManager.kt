package edu.washington.hoganc17.annoyingex.manager

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class TimedMessageManager(context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startMessages() {
        if(!isMessageWorkerRunning()) {
            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<MessageWorker>(20, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .addTag(WORKER_TAG)
                .build()

            workManager.enqueue(workRequest)
        }
    }

    private fun isMessageWorkerRunning(): Boolean {
        workManager.getWorkInfosByTag(WORKER_TAG).get().forEach { workInfo ->
           if (workInfo.state === WorkInfo.State.ENQUEUED || workInfo.state === WorkInfo.State.RUNNING) {
               return true
           }
        }
        return false
    }

    fun stopMessages() {
        workManager.cancelAllWorkByTag(WORKER_TAG)
    }

    companion object {
        const val WORKER_TAG = "WORKER_TAG"
    }
}