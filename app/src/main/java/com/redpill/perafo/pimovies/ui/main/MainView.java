package com.redpill.perafo.pimovies.ui.main;

public interface MainView {

    interface HomeView{

        void goToMain();

        void goToSearch();

        void goToFavorites();
    }

    interface View{

        void setPopularMovies();
    }

    interface Presenter{

       void setPopularMovies();
    }

    interface Interactor{

        void getPopularMovies();
    }
}
