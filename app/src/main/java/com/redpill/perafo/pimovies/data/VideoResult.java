package com.redpill.perafo.pimovies.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<VideoDetails> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<VideoDetails> getResults() {
        return results;
    }

    public void setResults(List<VideoDetails> results) {
        this.results = results;
    }
}
