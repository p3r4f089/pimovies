package com.redpill.perafo.pimovies.ui.main;

import android.content.Context;

public class MainPresenter implements MainView.Presenter {

    private MainView.View view;
    private MainInteractor interactor;


    MainPresenter(Context context, MainView.View view) {
        this.view = view;

        interactor = new MainInteractor(context, this);
    }

    @Override
    public void setPopularMovies() {

    }
}
