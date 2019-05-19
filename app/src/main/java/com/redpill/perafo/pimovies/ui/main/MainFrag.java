package com.redpill.perafo.pimovies.ui.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.adapters.MediaAdapter;
import com.redpill.perafo.pimovies.data.PopularDetailsResult;
import com.redpill.perafo.pimovies.data.PopularResult;
import com.redpill.perafo.pimovies.utils.AlertsProvider;

import java.util.List;
import java.util.Objects;

public class MainFrag extends Fragment implements View.OnClickListener, MainView.View {

    public static final String TAG = "MainFrag";

    MainPresenter presenter;
    AlertsProvider alertsProvider;

    RecyclerView rv_popular;
    RecyclerView rv_popular_0;
    RecyclerView rv_popular_1;
    RecyclerView rv_popular_2;

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
                break;
            case R.id.card_top_rated:
                break;
            case R.id.card_upcoming:
                break;
        }
    }

    @Override
    public void setPopularMovies(PopularResult popular) {

        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            MediaAdapter mediaAdapter = new MediaAdapter(getActivity(), popular.getResults());

            if(popular.getPage() == 1){
                rv_popular.setAdapter(mediaAdapter);
            }else if(popular.getPage() == 2){
                rv_popular_0.setAdapter(mediaAdapter);
            }else if(popular.getPage() == 3){
                rv_popular_1.setAdapter(mediaAdapter);
            }else if(popular.getPage() == 4){
                rv_popular_2.setAdapter(mediaAdapter);
            }

            mediaAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public void showMessage(String title, String message) {
        alertsProvider.showBasicAlert(title, message);
    }
}
