package edu.washington.hoganc17.annoyingex.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.washington.hoganc17.annoyingex.R
import edu.washington.hoganc17.annoyingex.manager.AnnoyingExNotificationManager.Companion.MESSAGE_KEY
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        val message = intent.getStringExtra(MESSAGE_KEY)

        message?.let {
            tvMessage.text = message
        }
    }
}
