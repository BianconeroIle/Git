package com.example.ilijaangeleski.gitrepos.callback;

import com.example.ilijaangeleski.gitrepos.model.GitRepositories;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */

public interface RepositoriesCallback {
    void onSuccess(GitRepositories response);

    void onFailure(Throwable t);
}
