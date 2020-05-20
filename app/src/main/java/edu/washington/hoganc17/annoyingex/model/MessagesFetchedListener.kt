package edu.washington.hoganc17.annoyingex.model

interface MessagesFetchListener {
    fun onMessagesFetched(messages: List<String>)
    fun onFetchError()
}