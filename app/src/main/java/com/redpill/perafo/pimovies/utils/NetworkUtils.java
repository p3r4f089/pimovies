package com.redpill.perafo.pimovies.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.Objects;

public class NetworkUtils extends BroadcastReceiver {

    private static final String TAG = "NetworkManagerProvider";

    private Context context;
    private NetworkHandler handler;
    private ConnectivityManager connectivityManager;

    public interface NetworkHandler {
        void onNetworkUpdate(boolean isOnline);
    }

    public NetworkUtils(Context context, NetworkHandler handler) {
        this.context = context;
        this.handler = handler;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isOnline() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void start() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(this, filter);
    }

    public void stop() {
        Log.d(TAG, "NetworkManager (STOP)");
        context.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), ConnectivityManager.CONNECTIVITY_ACTION) && handler != null) {
            boolean isOnline = isOnline();
            Log.i(TAG, "network " + (isOnline ? "on" : "off"));
            handler.onNetworkUpdate(isOnline);
        }
    }

}
