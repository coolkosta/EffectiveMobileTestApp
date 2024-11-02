package com.coolkosta.effectivemobiletestapp

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.coolkosta.effectivemobiletestapp.utill.common.AppStartTimeCacheDelegate
import com.coolkosta.effectivemobiletestapp.utill.common.AppStartTimeCacheDelegateImpl

class App : Application(), AppStartTimeCacheDelegate by AppStartTimeCacheDelegateImpl() {
    override fun onCreate() {
        super.onCreate()
        setLifeCycle(name = "App", lifecycleOwner = ProcessLifecycleOwner.get())
    }
}