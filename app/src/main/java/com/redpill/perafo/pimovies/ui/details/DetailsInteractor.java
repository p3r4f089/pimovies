package com.redpill.perafo.pimovies.ui.details;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.data.MediaDetails;
import com.redpill.perafo.pimovies.data.VideoDetails;
import com.redpill.perafo.pimovies.data.VideoResult;
import com.redpill.perafo.pimovies.services.ApiMovies;

public class DetailsInteractor implements DetailsView.Interactor, ApiMovies.OnApiResponseListener {

    private int VIDEO_TIME_LOAD = 800;

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
            Handler handler = new Handler();
            handler.postDelayed(() -> api.getMovieVideos(String.valueOf(id)), VIDEO_TIME_LOAD);

        }else if(type.equals("tv")){
            api.getTvDetails(String.valueOf(id));
            Handler handler = new Handler();
            handler.postDelayed(() -> api.getTvVideos(String.valueOf(id)), VIDEO_TIME_LOAD);

        }else if(type.equals("movie")){
            api.getMoviesDetails(String.valueOf(id));
            Handler handler = new Handler();
            handler.postDelayed(() -> api.getMovieVideos(String.valueOf(id)), VIDEO_TIME_LOAD);
        }
    }

    @Override
    public void onApiResponse(int statusCode, String data, int mode) {
        if(statusCode == 200){
            GsonBuilder builder = new GsonBuilder();
            Gson mGson = builder.create();
            if(mode == 0){
                MediaDetails mediaDetails = mGson.fromJson(data, MediaDetails.class);
                presenter.setDetails(mediaDetails);
            }else if(mode == 1){
                VideoResult videoResult = mGson.fromJson(data, VideoResult.class);

                for( VideoDetails video : videoResult.getResults()){
                    Log.d("TAG VIDEO ",  video.getKey() + " " + video.getSite());
                    if(video.getSite().equals("YouTube")){
                        Log.d("TAG", "set youtube video");
                        presenter.setVideo(video.getKey());
                    }

                }
            }

        }else{
            presenter.setError(context.getString(R.string.api_error_title), context.getString(R.string.api_error));
        }
    }
}
