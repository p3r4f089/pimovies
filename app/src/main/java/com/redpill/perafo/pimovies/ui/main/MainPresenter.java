package com.redpill.perafo.pimovies.ui.main;

import android.content.Context;

import com.redpill.perafo.pimovies.data.PopularDetailsResult;
import com.redpill.perafo.pimovies.data.PopularResult;

import java.util.List;

public class MainPresenter implements MainView.Presenter {

    private MainView.View view;
    private MainInteractor interactor;


    MainPresenter(Context context, MainView.View view) {
        this.view = view;

        interactor = new MainInteractor(context, this);
    }


    @Override
    public void setPopularMovies(List<PopularDetailsResult> popular) {
        view.setPopularMovies(popular);
    }

    @Override
    public void setError(String title, String message) {
        view.showMessage(title, message);
    }


}
