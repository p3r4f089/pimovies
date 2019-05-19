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

    public void getMovies(String page, int type){

        String url = "";

        if(type == 0){
            url = Config.API_HOST + Config.API_VERSION + "/" + Config.MOVIE_POPULAR_PATH;
            mode = 1;
        }else if(type == 1){
            url = Config.API_HOST + Config.API_VERSION + "/" + Config.MOVIE_TOPRATED_PATH;
            mode = 2;
        }else if(type == 2){
            url = Config.API_HOST + Config.API_VERSION + "/" + Config.MOVIE_UPCOMING_PATH;
            mode = 3;
        }



        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder()
                .addQueryParameter("api_key", Config.MOVIEDB_API_KEY)
                .addQueryParameter("page", page)
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


    public void search(String query){

        String url = Config.API_HOST + Config.API_VERSION + "/" + Config.SEARCH_PATH;
        mode = 4;
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder()
                .addQueryParameter("api_key", Config.MOVIEDB_API_KEY)
                .addQueryParameter("query", query)
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
