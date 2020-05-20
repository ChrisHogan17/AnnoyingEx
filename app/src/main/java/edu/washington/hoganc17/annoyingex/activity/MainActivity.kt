package edu.washington.hoganc17.annoyingex.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import edu.washington.hoganc17.annoyingex.AnnoyingExApp
import edu.washington.hoganc17.annoyingex.model.MessagesFetchListener
import edu.washington.hoganc17.annoyingex.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    MessagesFetchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationManager = (application as AnnoyingExApp).annoyingExNotificationManager
        notificationManager.messagesFetchListener = this

        btnStartNotifications.setOnClickListener {
            notificationManager.sendRandomMessageNotification()
        }
    }

    override fun onMessagesFetched(messages: List<String>) {
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
}
