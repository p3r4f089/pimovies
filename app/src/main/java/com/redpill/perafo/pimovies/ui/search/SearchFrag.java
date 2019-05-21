package com.redpill.perafo.pimovies.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.adapters.MediaAdapter;
import com.redpill.perafo.pimovies.data.Result;
import com.redpill.perafo.pimovies.utils.AlertsProvider;

import java.util.Objects;

public class SearchFrag extends Fragment implements com.redpill.perafo.pimovies.ui.search.SearchView.View {

    public static final String TAG = "MainFrag";

    SearchPresenter presenter;
    AlertsProvider alertsProvider;

    RecyclerView rv_search;
    SearchView searchView;

    public static Fragment newInstance() {
        return new SearchFrag();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new SearchPresenter(getActivity(), this);
        alertsProvider = new AlertsProvider(getActivity());

        searchView = view.findViewById(R.id.search_view);
        rv_search = view.findViewById(R.id.rv_search);

        searchView.requestFocus();

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);

        rv_search.setLayoutManager(layoutManager);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.length() > 2){
                    Log.d(TAG, "search query " + s);
                    presenter.searchMovies(s);
                }
                return false;
            }
        });
    }


    @Override
    public void setMovies(Result result) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            MediaAdapter mediaAdapter = new MediaAdapter(getActivity(), result.getResults());
            rv_search.setAdapter(mediaAdapter);
            mediaAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void setError(String title, String message) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            alertsProvider.showBasicAlert(title, message);
        });
    }
}
