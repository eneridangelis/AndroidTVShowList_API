package com.example.listagem.repository.network.moviedb;

import com.example.listagem.model.TvShowList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("discover/tv?include_null_first_air_dates=false&timezone=America%2FNew_York&page=1&sort_by=popularity.desc&language=en-US&api_key=4fe01878cf635fe522e9e11532537b3b")
    Call<TvShowList> getAllTvShows();
}
