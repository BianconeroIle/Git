package com.example.ilijaangeleski.gitrepos.presenter;

import com.example.ilijaangeleski.gitrepos.callback.RepositoriesCallback;
import com.example.ilijaangeleski.gitrepos.manager.RepositoriesManager;
import com.example.ilijaangeleski.gitrepos.model.GitRepo;
import com.example.ilijaangeleski.gitrepos.model.GitRepositories;
import com.example.ilijaangeleski.gitrepos.view.RepositoriesView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */

public class RepositoriesPresenter {
    private RepositoriesManager repositoriesManager;
    private WeakReference<RepositoriesView> repositoriesViewWeakReference;
    private List<GitRepo> repositories = new ArrayList<>();

    public RepositoriesPresenter(WeakReference<RepositoriesView> view, RepositoriesManager repositoriesManager) {
        this.repositoriesManager = repositoriesManager;
        this.repositoriesViewWeakReference = view;
    }

    public void onTextChange(final String query) {
        repositoriesManager.fetchRepositories(query, new RepositoriesCallback() {
            @Override
            public void onSuccess(GitRepositories response) {
                RepositoriesView view = repositoriesViewWeakReference.get();
                if (view != null) {
                    if (response != null
                            && response.getResults() != null
                            && !response.getResults().isEmpty()) {
                        repositories.clear();
                        repositories.addAll(response.getResults());
                        view.updateView();
                    } else {
                        repositories.clear();
                        view.updateView();

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                RepositoriesView view = repositoriesViewWeakReference.get();
                if(view != null){
                    view.showErrorGettingRepositories();
                }
            }
        });
    }

    public List<GitRepo> getRepositories() {
        return repositories;
    }
}
