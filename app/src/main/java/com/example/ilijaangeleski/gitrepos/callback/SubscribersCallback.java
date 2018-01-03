package com.example.ilijaangeleski.gitrepos.callback;

import com.example.ilijaangeleski.gitrepos.model.GitSubscribers;

import java.util.List;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */

public interface SubscribersCallback {
    void onSuccess(List<GitSubscribers> response);

    void onFailure(Throwable t);
}
