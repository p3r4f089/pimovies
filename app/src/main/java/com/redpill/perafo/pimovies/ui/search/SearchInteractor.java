package com.redpill.perafo.pimovies.ui.search;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.data.Result;
import com.redpill.perafo.pimovies.services.ApiMovies;

public class SearchInteractor implements SearchView.Interactor, ApiMovies.OnApiResponseListener {

    private  SearchPresenter presenter;
    private ApiMovies api;
    private Context context;


    SearchInteractor(Context context, SearchPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        api = new ApiMovies(context, this);
    }

    @Override
    public void searchMovies(String query) {
        api.search(query);
    }

    @Override
    public void onApiResponse(int statusCode, String data) {
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
