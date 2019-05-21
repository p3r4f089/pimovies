package com.redpill.perafo.pimovies.ui.details;

import com.redpill.perafo.pimovies.data.MediaDetails;


public interface DetailsView {

    interface View{

        void setDetails(MediaDetails mediaDetails);

        void setError(String title, String message);
    }


    interface Presenter{

        void setDetails(MediaDetails mediaDetails);

        void getDetails(String type, int id);

        void setError(String title, String message);
    }

    interface Interactor{
        void getDetails(String type, int id);
    }
}
