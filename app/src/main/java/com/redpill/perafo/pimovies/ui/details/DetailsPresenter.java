package com.redpill.perafo.pimovies.ui.details;

import android.content.Context;

import com.redpill.perafo.pimovies.data.MediaDetails;


public class DetailsPresenter implements DetailsView.Presenter{

    private DetailsView.View view;
    private DetailsInteractor interactor;

    DetailsPresenter(Context context, DetailsView.View view) {
        this.view = view;
        interactor = new DetailsInteractor(context, this);
    }

    @Override
    public void setDetails(MediaDetails mediaDetails) {
        view.setDetails(mediaDetails);
    }

    @Override
    public void getDetails(String type, int id) {
        interactor.getDetails(type, id);
    }

    @Override
    public void setError(String title, String message) {
        view.setError(title, message);
    }
}
