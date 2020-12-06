package com.increments.riseuplabs.daos;

import com.increments.riseuplabs.models.Movie;
import com.increments.riseuplabs.models.MoviePack;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

import static com.increments.riseuplabs.utils.Constants.MOVIE_QUERY_URL;

public interface Api {
    @GET(MOVIE_QUERY_URL)
    Call<List<MoviePack>> getMovies();

    @GET
    Call<Movie> getMovie(@Url String url);
}
