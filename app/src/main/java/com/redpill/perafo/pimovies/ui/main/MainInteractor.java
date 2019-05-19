package com.redpill.perafo.pimovies.ui.main;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.data.PopularResult;
import com.redpill.perafo.pimovies.services.ApiMovies;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;


public class MainInteractor implements MainView.Interactor, ApiMovies.OnApiResponseListener {

    private static final String TAG = "MainInteractor";

    private int LOAD_TIME = 100;

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
        api.getPopularMovies();
    }

    @Override
    public void onApiResponse(int statusCode, String data, int mode) {
        if(statusCode == 200){
            GsonBuilder builder = new GsonBuilder();
            Gson mGson = builder.create();

            switch (mode){

                case 1:
                    PopularResult popular = mGson.fromJson(data, PopularResult.class);
                    presenter.setPopularMovies(popular.getResults());
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }


        }else{
            presenter.setError(context.getString(R.string.api_error_title), context.getString(R.string.api_error));
        }
    }
}
