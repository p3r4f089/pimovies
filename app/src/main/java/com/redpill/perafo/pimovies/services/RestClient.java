package com.redpill.perafo.pimovies.services;

import android.util.Log;

import com.redpill.perafo.pimovies.Config;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RestClient {

    private static final String TAG = "RestClient";

    private OkHttpClient client = new OkHttpClient();

    public RestClient() {

    }

    void get(HttpUrl url, Callback callback) throws IOException {
        //String ACCESS_TOKEN = "Bearer " + Config.MOVIEDB_TOKEN;
        Log.d(TAG, "get url " + url);
        Request request = new Request.Builder()
                .url(url)
                //.header("Authorization", ACCESS_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

        /*try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }*/
    }
}
