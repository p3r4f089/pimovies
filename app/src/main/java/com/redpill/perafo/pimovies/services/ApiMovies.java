package com.redpill.perafo.pimovies.services;

import android.content.Context;
import android.util.Log;

import com.redpill.perafo.pimovies.Config;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

public class ApiMovies {

    private static final String TAG = "ApiMovies";

    private RestClient client;
    private OnApiResponseListener responseListener;
    private Context context;

    private int mode;

    public ApiMovies(Context context, OnApiResponseListener responseListener) {
        this.context = context;
        this.responseListener = responseListener;

        client = new RestClient();
    }

    public void getPopularMovies(){
        String url = Config.API_HOST + Config.API_VERSION + "/" + Config.MOVIE_POPULAR_PATH;
        mode = 1;
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder()
                .addQueryParameter("api_key", Config.MOVIEDB_API_KEY)
                .build();

        try {
            client.get(httpUrl, new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d(TAG, "Login onFailure " + e.getMessage() + " " + 404);
                    responseListener.onApiResponse(404, null, mode);
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    assert response.body() != null;
                    String result = response.body().string();
                    int code = response.code();
                    Log.d(TAG, "getPopularMovies Response " + code + " " + result );
                    responseListener.onApiResponse(code, result, mode);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface OnApiResponseListener{
        void onApiResponse(int statusCode , String data, int mode);
    }
}
