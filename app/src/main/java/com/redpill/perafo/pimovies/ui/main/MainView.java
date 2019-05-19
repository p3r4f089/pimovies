package com.redpill.perafo.pimovies.ui.main;

import com.redpill.perafo.pimovies.data.PopularDetailsResult;
import com.redpill.perafo.pimovies.data.PopularResult;

import java.util.List;

public interface MainView {

    interface HomeView{

        void goToMain();

        void goToSearch();

        void goToFavorites();
    }

    interface View{

        void setPopularMovies(List<PopularDetailsResult> popular);

        void showMessage(String title, String message);
    }

    interface Presenter{

       void setPopularMovies(List<PopularDetailsResult> popular);

       void setError(String title, String message);
    }

    interface Interactor{

        void getPopularMovies();
    }
}
