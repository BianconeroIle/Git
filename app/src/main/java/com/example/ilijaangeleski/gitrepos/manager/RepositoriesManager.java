package com.example.ilijaangeleski.gitrepos.manager;

import android.util.Log;

import com.example.ilijaangeleski.gitrepos.api.NetworkApi;
import com.example.ilijaangeleski.gitrepos.callback.RepositoriesCallback;
import com.example.ilijaangeleski.gitrepos.model.GitRepositories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */

public class RepositoriesManager {
    private NetworkApi networkApi;

    public RepositoriesManager(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public void fetchRepositories(String query, final RepositoriesCallback callback){
        Call<GitRepositories> call = networkApi.fetchRepositories(query);
        call.enqueue(new Callback<GitRepositories>() {
            @Override
            public void onResponse(Call<GitRepositories> call, Response<GitRepositories> response) {
                Log.d("fetchRepositories","onResponse");
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GitRepositories> call, Throwable t) {
                Log.d("fetchRepositories","OnFailure");
                callback.onFailure(t);
            }
        });
    }
}
