package com.redpill.perafo.pimovies.ui.search;

import android.content.Context;

import com.redpill.perafo.pimovies.data.Result;

public class SearchPresenter implements SearchView.Presenter {

    private SearchView.View view;
    private SearchInteractor interactor;

    public SearchPresenter(Context context, SearchView.View view) {
        this.view = view;

        interactor = new SearchInteractor(context, this);
    }

    @Override
    public void setMovies(Result result) {
        view.setMovies(result);
    }

    @Override
    public void searchMovies(String query) {
        interactor.searchMovies(query);
    }

    @Override
    public void setError(String title, String message) {
        view.setError(title, message);
    }
}
