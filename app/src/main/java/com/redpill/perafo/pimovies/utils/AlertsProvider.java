package com.redpill.perafo.pimovies.utils;

import android.app.AlertDialog;
import android.content.Context;

import com.redpill.perafo.pimovies.R;

public class AlertsProvider {

    private static final String TAG = "AlertsProvider";
    private Context context;

    public AlertsProvider(Context context) {

        this.context = context;
    }

    public void showBasicAlert(String title, String message){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.ok_btn), (dialog, which) -> {

                }).show();
    }
}
