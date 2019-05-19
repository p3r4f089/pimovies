package com.redpill.perafo.pimovies.ui.main;

import com.redpill.perafo.pimovies.data.Result;

public interface MainView {

    interface HomeView{

        void goToMain();

        void goToSearch();

        void goToFavorites();
    }

    interface View{

        void setMovies(Result result);

        void showMessage(String title, String message);
    }

    interface Presenter{

        void setMovies(Result result);

        void getPopularMovies();

        void getTopRatedMovies();

        void getUpComingMovies();

       void setError(String title, String message);


    }

    interface Interactor{

        void getPopularMovies();

        void getTopRatedMovies();

        void getUpComingMovies();
    }
}
