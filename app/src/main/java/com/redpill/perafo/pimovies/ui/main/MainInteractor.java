package com.redpill.perafo.pimovies.ui.main;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.data.Result;
import com.redpill.perafo.pimovies.services.ApiMovies;


public class MainInteractor implements MainView.Interactor, ApiMovies.OnApiResponseListener {

    private static final String TAG = "MainInteractor";

    private int LOAD_TIME = 100;
    private int MOVIES_ROWS = 4;

    private MainPresenter presenter;
    private ApiMovies api;
    private Context context;


    MainInteractor(Context context, MainPresenter presenter) {
        this.presenter = presenter;
        this.context = context;

        api = new ApiMovies(context, this);

        Handler handler = new Handler();
        handler.postDelayed(() -> getPopularMovies(), LOAD_TIME);
    }

    @Override
    public void getPopularMovies() {
        for(int i = 0; i < MOVIES_ROWS; i++){
            Log.d(TAG, "rows movies " + i);
            api.getMovies(String.valueOf(i + 1), 0);
        }
    }

    @Override
    public void getTopRatedMovies() {
        for(int i = 0; i < MOVIES_ROWS; i++){
            Log.d(TAG, "rows movies " + i);
            api.getMovies(String.valueOf(i + 1), 1);
        }
    }

    @Override
    public void getUpComingMovies() {
        for(int i = 0; i < MOVIES_ROWS; i++){
            Log.d(TAG, "rows movies " + i);
            api.getMovies(String.valueOf(i + 1), 2);
        }
    }

    @Override
    public void onApiResponse(int statusCode, String data, int mode) {
        if(statusCode == 200){
            GsonBuilder builder = new GsonBuilder();
            Gson mGson = builder.create();
            Result result = mGson.fromJson(data, Result.class);
            presenter.setMovies(result);

        }else{
            presenter.setError(context.getString(R.string.api_error_title), context.getString(R.string.api_error));
        }
    }
}
