package com.google.samples.quickstart.config.kotlin

interface RemoteConfigManager {
    fun initializeConfig()
    fun fetchLiveUpdates()
}