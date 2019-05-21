package com.redpill.perafo.pimovies.ui.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redpill.perafo.pimovies.R;

public class DetailsFrag extends Fragment {

    public static final String TAG = "DetailsPresenter";

    public static Fragment newInstance(int id) {
        DetailsFrag detailsFrag = new DetailsFrag();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        detailsFrag.setArguments(bundle);
        return detailsFrag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_frag, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        int id = getArguments().getInt("id");

        Log.d(TAG, "ID DETAILS "+ id);
    }
}
