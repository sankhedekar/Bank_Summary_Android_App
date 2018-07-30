package com.example.sankhedekar.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkType extends BroadcastReceiver {

    public static NetworkTypeListener NetworkTypeListener;

    public NetworkType() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        // For "Not connected to Internet"
        int connectedType = 0;
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                connectedType = 1;
            }
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                connectedType = 2;
            }
        }

        if (NetworkTypeListener != null) {
            NetworkTypeListener.onNetworkConnectionChanged(connectedType);
        }
    }

    public interface NetworkTypeListener {
        void onNetworkConnectionChanged(int connectedType);
    }
}
