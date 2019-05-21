package com.redpill.perafo.pimovies.ui.details;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.data.MediaDetails;
import com.redpill.perafo.pimovies.data.Result;
import com.redpill.perafo.pimovies.services.ApiMovies;

public class DetailsInteractor implements DetailsView.Interactor, ApiMovies.OnApiResponseListener {

    private DetailsPresenter presenter;
    private ApiMovies api;
    private Context context;

    DetailsInteractor(Context context, DetailsPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        api = new ApiMovies(context, this);
    }

    @Override
    public void getDetails(String type, int id) {

        if(type == null){
            api.getMoviesDetails(String.valueOf(id));
        }else if(type.equals("tv")){
            api.getTvDetails(String.valueOf(id));
        }else if(type.equals("movie")){
            api.getMoviesDetails(String.valueOf(id));
        }
    }

    @Override
    public void onApiResponse(int statusCode, String data) {
        if(statusCode == 200){
            GsonBuilder builder = new GsonBuilder();
            Gson mGson = builder.create();
            MediaDetails mediaDetails = mGson.fromJson(data, MediaDetails.class);
            presenter.setDetails(mediaDetails);

        }else{
            presenter.setError(context.getString(R.string.api_error_title), context.getString(R.string.api_error));
        }
    }
}
