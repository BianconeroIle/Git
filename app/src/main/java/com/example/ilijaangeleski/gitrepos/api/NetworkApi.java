package com.example.ilijaangeleski.gitrepos.api;

import com.example.ilijaangeleski.gitrepos.model.GitRepositories;
import com.example.ilijaangeleski.gitrepos.model.GitSubscribers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */

public interface NetworkApi {

    String BASE_URL = "https://api.github.com/";

    @GET("search/repositories")
    Call<GitRepositories> fetchRepositories(@Query("q") String query);

    @GET
    Call<List<GitSubscribers>> fetchSubscribers(@Url String url);
}
