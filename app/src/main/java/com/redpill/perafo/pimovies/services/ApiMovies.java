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

    private void sendRequest(HttpUrl url){
        try {
            client.get(url, new Callback() {
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

    public void getMovies(String page, int type){

        mode = 0;

        String url = "";

        if(type == 0){
            url = Config.API_HOST + Config.API_VERSION + "/" + Config.MOVIE_POPULAR_PATH;
        }else if(type == 1){
            url = Config.API_HOST + Config.API_VERSION + "/" + Config.MOVIE_TOPRATED_PATH;
        }else if(type == 2){
            url = Config.API_HOST + Config.API_VERSION + "/" + Config.MOVIE_UPCOMING_PATH;
        }

        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder()
                .addQueryParameter("api_key", Config.MOVIEDB_API_KEY)
                .addQueryParameter("page", page)
                .build();

        sendRequest(httpUrl);

    }


    public void search(String query){
        mode = 0;
        String url = Config.API_HOST + Config.API_VERSION + "/" + Config.SEARCH_PATH;
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder()
                .addQueryParameter("api_key", Config.MOVIEDB_API_KEY)
                .addQueryParameter("query", query)
                .build();

       sendRequest(httpUrl);

    }

    public void getMoviesDetails(String movieId){
        mode = 0;
        String url = Config.API_HOST + Config.API_VERSION + "/" + Config.MOVIE_PATH + "/" + movieId;
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder()
                .addQueryParameter("api_key", Config.MOVIEDB_API_KEY)
                .build();

        sendRequest(httpUrl);

    }

    public void getTvDetails(String tvId){
        mode = 0;
        String url = Config.API_HOST + Config.API_VERSION + "/" + Config.TV_PATH + "/" + tvId;
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder()
                .addQueryParameter("api_key", Config.MOVIEDB_API_KEY)
                .build();

        sendRequest(httpUrl);

    }

    public void getMovieVideos(String movieId){
        mode = 1;
        String url = Config.API_HOST + Config.API_VERSION + "/" + Config.MOVIE_PATH +  "/" + movieId + Config.VIDEO_PATH ;
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder()
                .addQueryParameter("api_key", Config.MOVIEDB_API_KEY)
                .build();

        sendRequest(httpUrl);

    }

    public void getTvVideos(String tvId){
        mode = 1;
        String url = Config.API_HOST + Config.API_VERSION + "/" +  Config.TV_PATH + "/" + tvId + Config.VIDEO_PATH;
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder()
                .addQueryParameter("api_key", Config.MOVIEDB_API_KEY)
                .build();

        sendRequest(httpUrl);
    }

    public interface OnApiResponseListener{
        void onApiResponse(int statusCode , String data, int mode);
    }
}
