package com.redpill.perafo.pimovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.redpill.perafo.pimovies.Config;
import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.data.DetailsResult;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ModelsViewHolder> implements Filterable {

    private static final String TAG = "MediaAdapter";

    private List<DetailsResult> data;
    private Context context;

    public MediaAdapter(Context context, List<DetailsResult> data) {
       this.data = data;
       this.context = context;

       Log.d(TAG, "DATA SIZE " + data.size());
    }

    class ModelsViewHolder extends RecyclerView.ViewHolder {

        ImageView img_preview;

        ModelsViewHolder(View v) {
            super(v);
            img_preview = v.findViewById(R.id.card_preview);
        }
    }

    @Override
    public ModelsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ModelsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ModelsViewHolder holder, int position) {
        DetailsResult item = data.get(position);

        //Log.d(TAG, "item " + item.getTitle());
        String url = getUrlPreviewImage(item.getPosterPath());
        //Log.d(TAG, "Preview Url " + url);
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.pimovies_logo)
                .into(holder.img_preview);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    private String getUrlPreviewImage(String path){
        return Config.IMAGES_PATH + Config.IMAGES_SIZE_W500 + path;
    }
}
