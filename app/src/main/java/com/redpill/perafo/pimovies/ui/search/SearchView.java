package com.redpill.perafo.pimovies.ui.search;

import com.redpill.perafo.pimovies.data.Result;

public interface SearchView {

    interface View{
        void setMovies(Result result);

        void setError(String title, String message);
    }

    interface Presenter{
        void setMovies(Result result);

        void searchMovies(String query);

        void setError(String title, String message);
    }

    interface Interactor{
        void searchMovies(String query);
    }
}
