package com.argz.issue4864;

import android.app.Application;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseRemoteConfigManager remoteConfigManager = new FirebaseRemoteConfigManager(FirebaseRemoteConfig.getInstance());
        remoteConfigManager.initializeConfig();
        remoteConfigManager.fetchLiveUpdates();
    }
}
