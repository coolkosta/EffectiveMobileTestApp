package com.coolkosta.effectivemobiletestapp.utill.common

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

interface AppStartTimeCacheDelegate {
    fun setLifeCycle(name: String, lifecycleOwner: LifecycleOwner)
}

class AppStartTimeCacheDelegateImpl : AppStartTimeCacheDelegate, LifecycleEventObserver {
    private var lifecycleOwner: LifecycleOwner? = null
    private lateinit var name: String
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy, HH:mm", Locale.getDefault())
    private val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    override fun setLifeCycle(name: String, lifecycleOwner: LifecycleOwner) {
        this.name = name
        this.lifecycleOwner = lifecycleOwner
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            val startTime = System.currentTimeMillis()
            executor.scheduleWithFixedDelay({
                val cashedTime = dateFormat.format(startTime)
                Log.d("AppStartTimeCacheDelegate", "$name started in $cashedTime")
            }, 0, 3, TimeUnit.SECONDS)
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            lifecycleOwner?.lifecycle?.removeObserver(this)
            lifecycleOwner = null
            executor.shutdown()
        }
    }
}
