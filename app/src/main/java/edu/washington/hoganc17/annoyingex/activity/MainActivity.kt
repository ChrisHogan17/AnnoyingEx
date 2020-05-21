package edu.washington.hoganc17.annoyingex.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import edu.washington.hoganc17.annoyingex.AnnoyingExApp
import edu.washington.hoganc17.annoyingex.model.MessagesFetchListener
import edu.washington.hoganc17.annoyingex.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    MessagesFetchListener {

    private var hasFetched = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            val prevFetchedStatus = savedInstanceState.getBoolean(OUT_HAS_FETCHED)
            hasFetched = prevFetchedStatus
        }

        if (hasFetched) {
            progressSpinner.visibility = View.GONE
            tvLoading.visibility = View.GONE
        }

        val notificationManager = (application as AnnoyingExApp).annoyingExNotificationManager
        notificationManager.messagesFetchListener = this

        val timedMessageManager = (application as AnnoyingExApp).timedMessageManager

        btnStartNotifications.setOnClickListener {
            timedMessageManager.startMessages()
        }

        btnStopNotifications.setOnClickListener {
            timedMessageManager.stopMessages()
        }
    }

    override fun onMessagesFetched(messages: List<String>) {
        hasFetched = true
        progressSpinner.visibility = View.GONE
        tvLoading.visibility = View.GONE
        btnStartNotifications.visibility = View.VISIBLE
        btnStopNotifications.visibility = View.VISIBLE
    }

    override fun onFetchError() {
        Toast.makeText(this, "Error retrieving messages", Toast.LENGTH_SHORT).show()
        tvLoading.text = getString(R.string.errorRetrievingMessages)
        progressSpinner.visibility = View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putBoolean(OUT_HAS_FETCHED, hasFetched)
    }

    companion object {
        const val OUT_HAS_FETCHED = "OUT_HAS_FETCHED"
    }
}
