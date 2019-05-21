package com.redpill.perafo.pimovies.ui.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.data.MediaDetails;
import com.redpill.perafo.pimovies.ui.main.MainAct;
import com.redpill.perafo.pimovies.utils.AlertsProvider;

import java.util.Objects;

public class DetailsFrag extends Fragment implements View.OnClickListener, DetailsView.View {

    public static final String TAG = "DetailsPresenter";

    YouTubePlayerView youTubePlayerView;
    DetailsPresenter presenter;
    AlertsProvider alertsProvider;

    TextView details_back;
    TextView details_title;
    TextView details_vote_count;
    TextView details_vote_average;
    TextView details_popularity;
    TextView details_original_language;
    TextView details_release_date;
    TextView details_overview;

    public static Fragment newInstance(int id, String mediaType) {
        DetailsFrag detailsFrag = new DetailsFrag();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("mediaType", mediaType);
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
        String mediaType = getArguments().getString("mediaType");

        Log.d(TAG, "ID DETAILS "+ id + " MEDIA TYPE "+ mediaType);

        presenter = new DetailsPresenter(getActivity(), this);

        presenter.getDetails(mediaType, id);

        alertsProvider = new AlertsProvider(getActivity());

        details_back = view.findViewById(R.id.details_back);
        details_title = view.findViewById(R.id.details_title);
        details_vote_count = view.findViewById(R.id.details_vote_count);
        details_vote_average = view.findViewById(R.id.details_vote_average);
        details_popularity = view.findViewById(R.id.details_popularity);
        details_original_language = view.findViewById(R.id.details_original_language);
        details_release_date = view.findViewById(R.id.details_release_date);
        details_overview = view.findViewById(R.id.details_overview);

        youTubePlayerView = view.findViewById(R.id.youtube_player_view);

        details_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.details_back) {
            ((MainAct) Objects.requireNonNull(getActivity())).goToMain();
        }
    }

    @Override
    public void setDetails(MediaDetails mediaDetails) {

        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {

            String title = mediaDetails.getTitle();
            String voteCount = String.valueOf(mediaDetails.getVoteCount());
            String voteAverage = String.valueOf(mediaDetails.getVoteAverage());
            String popularity = String.valueOf(mediaDetails.getPopularity());
            String language = Objects.requireNonNull(getActivity()).getString(R.string.original_language) + " : " +  mediaDetails.getOriginalLanguage();
            String date = Objects.requireNonNull(getActivity().getString(R.string.release_date) + " : " + mediaDetails.getReleaseDate());
            String overview = mediaDetails.getOverview();

            Log.d(TAG, "title " + title);
            Log.d(TAG, "voteCount " + voteCount);
            Log.d(TAG, "voteAverage " + voteAverage);
            Log.d(TAG, "popularity " + popularity);
            Log.d(TAG, "overview " + overview);
            Log.d(TAG, "language " + language);
            Log.d(TAG, "date " + date);

            details_title.setText(title);
            details_vote_count.setText(voteCount);
            details_vote_average.setText(voteAverage);
            details_popularity.setText(popularity);
            details_original_language.setText(language);
            details_release_date.setText(date);
            details_overview.setText(overview);
        });
    }

    @Override
    public void setVideo(String id) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {

            youTubePlayerView.initialize(initializedYouTubePlayer -> initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady() {
                    initializedYouTubePlayer.loadVideo(id, 0);
                }
            }), true);

        });
    }


    @Override
    public void setError(String title, String message) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            alertsProvider.showBasicAlert(title, message);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }
}
