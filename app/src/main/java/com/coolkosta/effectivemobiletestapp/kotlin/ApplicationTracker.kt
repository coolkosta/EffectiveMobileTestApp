package com.coolkosta.effectivemobiletestapp.kotlin

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

interface AppStartTimeCacheDelegate {
    fun startLogging(name: String)
    fun stopLogging()
}

class AppStartTimeCacheDelegateImpl : AppStartTimeCacheDelegate {
    private lateinit var name: String
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy, HH:mm", Locale.getDefault())
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun startLogging(name: String) {
        this.name = name
        scope.launch {
            while (true) {
                val startTime = System.currentTimeMillis()
                val cashedTime = dateFormat.format(startTime)
                Log.d("AppStartTimeCacheDelegate", "$name started in $cashedTime")
                delay(3000)
            }
        }
    }

    override fun stopLogging() {
        scope.cancel()
    }
}
