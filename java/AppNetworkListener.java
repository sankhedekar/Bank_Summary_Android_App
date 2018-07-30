package com.example.sankhedekar.test;

import android.app.Application;

public class AppNetworkListener extends Application {

    private static AppNetworkListener appnetworkListener;

    @Override
    public void onCreate() {
        super.onCreate();
        appnetworkListener = this;
    }

    public static synchronized AppNetworkListener getInstance() {
        return appnetworkListener;
    }

    public void setConnectivityListener(NetworkType.NetworkTypeListener listener) {
        NetworkType.NetworkTypeListener = listener;
    }
}
