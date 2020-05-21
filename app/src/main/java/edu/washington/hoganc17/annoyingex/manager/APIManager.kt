package edu.washington.hoganc17.annoyingex.manager

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import edu.washington.hoganc17.annoyingex.model.Messages

class APIManager(context: Context) {
    private val queue = Volley.newRequestQueue(context)

    fun fetchMessages(onMessagesReady: (List<String>) -> Unit, onMessagesFetchError: (() -> Unit)? = null) {
        val fetchURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
        val stringRequest = StringRequest(Request.Method.GET, fetchURL,
            { response ->
                //success
                val gson = Gson()
                val retrievedMessages = gson.fromJson(response, Messages::class.java)
                onMessagesReady(retrievedMessages.messages)
            }, {
                onMessagesFetchError?.invoke()
            }
        )

        queue.add(stringRequest)
    }
}
