package com.redpill.perafo.pimovies.ui.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.adapters.MediaAdapter;
import com.redpill.perafo.pimovies.data.Result;
import com.redpill.perafo.pimovies.utils.AlertsProvider;

import java.util.Objects;

public class MainFrag extends Fragment implements View.OnClickListener, MainView.View {

    public static final String TAG = "MainFrag";

    MainPresenter presenter;
    AlertsProvider alertsProvider;

    RecyclerView rv_popular;
    RecyclerView rv_popular_0;
    RecyclerView rv_popular_1;
    RecyclerView rv_popular_2;

    CardView card_popular;
    CardView card_top_rated;
    CardView card_upcoming;

    public static Fragment newInstance(){
        return new MainFrag();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new MainPresenter(getActivity(), this);
        alertsProvider = new AlertsProvider(getActivity());

        rv_popular = view.findViewById(R.id.rv_popular);
        rv_popular_0 = view.findViewById(R.id.rv_popular_0);
        rv_popular_1 = view.findViewById(R.id.rv_popular_1);
        rv_popular_2 = view.findViewById(R.id.rv_popular_2);

        card_popular = view.findViewById(R.id.card_popular);
        card_top_rated = view.findViewById(R.id.card_top_rated);
        card_upcoming = view.findViewById(R.id.card_upcoming);

        card_popular.setOnClickListener(this);
        card_top_rated.setOnClickListener(this);
        card_upcoming.setOnClickListener(this);

        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager mLayout0 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager mLayout1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager mLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_popular.setLayoutManager(mLayout);
        rv_popular_0.setLayoutManager(mLayout0);
        rv_popular_1.setLayoutManager(mLayout1);
        rv_popular_2.setLayoutManager(mLayout2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_popular:
                presenter.getPopularMovies();
                break;
            case R.id.card_top_rated:
                presenter.getTopRatedMovies();
                break;
            case R.id.card_upcoming:
                presenter.getUpComingMovies();
                break;
        }
    }

    @Override
    public void setMovies(Result result) {

        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            MediaAdapter mediaAdapter = new MediaAdapter(getActivity(), result.getResults());

            if(result.getPage() == 1){
                rv_popular.setAdapter(mediaAdapter);
            }else if(result.getPage() == 2){
                rv_popular_0.setAdapter(mediaAdapter);
            }else if(result.getPage() == 3){
                rv_popular_1.setAdapter(mediaAdapter);
            }else if(result.getPage() == 4){
                rv_popular_2.setAdapter(mediaAdapter);
            }

            mediaAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public void showMessage(String title, String message) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            alertsProvider.showBasicAlert(title, message);
        });

    }
}
