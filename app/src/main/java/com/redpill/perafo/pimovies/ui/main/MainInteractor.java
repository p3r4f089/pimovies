package com.redpill.perafo.pimovies.ui.main;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.redpill.perafo.pimovies.services.ApiMovies;


public class MainInteractor implements MainView.Interactor {

    private static final String TAG = "MainInteractor";

    private int LOAD_TIME = 100;

    private MainPresenter presenter;
    private ApiMovies api;



    MainInteractor(Context context, MainPresenter presenter) {
        this.presenter = presenter;
        api = new ApiMovies();

        Handler handler = new Handler();
        handler.postDelayed(() -> getPopularMovies(), LOAD_TIME);
    }

    @Override
    public void getPopularMovies() {
        Log.d(TAG, "getPopularMovies");
        api.getPopularMovies();
    }
}
