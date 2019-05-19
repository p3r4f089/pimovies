package com.redpill.perafo.pimovies;

public class Config {

    public static final int SPLASH_TIME = 3000;

    /* API */
    public static final String MOVIEDB_TOKEN = BuildConfig.MovieDbToken;
    public static final String MOVIEDB_API_KEY = BuildConfig.MovieDbApiKey;

    public static final String IMAGES_PATH = "https://image.tmdb.org/t/p/";
    public static final String IMAGES_SIZE_ORIGINAL = "original";
    public static final String IMAGES_SIZE_W500 = "w500";

    public static final String API_HOST = "https://api.themoviedb.org/";
    public static final String API_VERSION ="3";

    public static final String MOVIE_PATH = "movie";
    public static final String MOVIE_POPULAR_PATH = "movie/popular";
    public static final String MOVIE_TOPRATED_PATH = "movie/top_rated";
    public static final String MOVIE_UPCOMING_PATH = "movie/upcoming";

    public static final String TV_PATH = "tv";
    public static final String TV_POPULAR_PATH = "tv/popular";
    public static final String TV_TOPRATED_PATH = "tv/top_rated";

}
