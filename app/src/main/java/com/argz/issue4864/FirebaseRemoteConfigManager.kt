package com.argz.issue4864

import android.util.Log
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.samples.quickstart.config.kotlin.RemoteConfigManager

private const val TAG = "FirebaseRemoteConfigMan"

class FirebaseRemoteConfigManager(private val firebaseRemoteConfig: FirebaseRemoteConfig):
    RemoteConfigManager {
    override fun initializeConfig() {
        Log.w(TAG, "Initialize remote config")
        with(firebaseRemoteConfig) {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 0L
                }
            )
            setDefaultsAsync(R.xml.remote_config_defaults)
        }
    }

    override fun fetchLiveUpdates() {
        Log.w(TAG, "Start live updates")
        firebaseRemoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                Log.w(TAG, "Updated keys: " + configUpdate.updatedKeys)
                firebaseRemoteConfig.activate()
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                Log.w(TAG, "Config update error with code: ${error.code}")
            }
        })
    }
}
