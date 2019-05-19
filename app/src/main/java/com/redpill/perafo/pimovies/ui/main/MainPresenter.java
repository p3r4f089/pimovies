package com.redpill.perafo.pimovies.ui.main;

import android.content.Context;

import com.redpill.perafo.pimovies.data.Result;


public class MainPresenter implements MainView.Presenter {

    private MainView.View view;
    private MainInteractor interactor;


    MainPresenter(Context context, MainView.View view) {
        this.view = view;

        interactor = new MainInteractor(context, this);
    }


    @Override
    public void setMovies(Result result) {
        view.setMovies(result);
    }

    @Override
    public void getPopularMovies() {
        interactor.getPopularMovies();
    }

    @Override
    public void getTopRatedMovies() {
        interactor.getTopRatedMovies();
    }

    @Override
    public void getUpComingMovies() {
        interactor.getUpComingMovies();
    }

    @Override
    public void setError(String title, String message) {
        view.showMessage(title, message);
    }


}
