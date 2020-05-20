package edu.washington.hoganc17.annoyingex

interface MessagesFetchListener {
    fun onMessagesFetched(messages: List<String>)
    fun onFetchError()
}