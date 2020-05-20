package edu.washington.hoganc17.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationManager = (application as AnnoyingExApp).annoyingExNotificationManager

        btnStartNotifications.setOnClickListener {
            notificationManager.startNotifications()
        }
    }
}
