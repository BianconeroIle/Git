package com.example.ilijaangeleski.gitrepos.manager;

import com.example.ilijaangeleski.gitrepos.api.NetworkApi;
import com.example.ilijaangeleski.gitrepos.callback.SubscribersCallback;
import com.example.ilijaangeleski.gitrepos.model.GitSubscribers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */

public class SubscribersRepositoriesManager {
    private NetworkApi networkApi;

    public SubscribersRepositoriesManager(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public void fetchSubscribers(String url, final SubscribersCallback callback) {
        Call<List<GitSubscribers>> call = networkApi.fetchSubscribers(url);
        call.enqueue(new Callback<List<GitSubscribers>>() {
            @Override
            public void onResponse(Call<List<GitSubscribers>> call, Response<List<GitSubscribers>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<GitSubscribers>> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }
}
