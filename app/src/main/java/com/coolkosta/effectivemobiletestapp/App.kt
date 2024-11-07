package com.coolkosta.effectivemobiletestapp

import android.app.Application
import com.coolkosta.effectivemobiletestapp.kotlin.AppStartTimeCacheDelegate
import com.coolkosta.effectivemobiletestapp.kotlin.AppStartTimeCacheDelegateImpl

class App : Application(), AppStartTimeCacheDelegate by AppStartTimeCacheDelegateImpl() {
    override fun onCreate() {
        super.onCreate()
        startLogging("App")
    }
}