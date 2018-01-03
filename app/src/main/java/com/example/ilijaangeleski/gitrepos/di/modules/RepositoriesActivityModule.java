package com.example.ilijaangeleski.gitrepos.di.modules;

import com.example.ilijaangeleski.gitrepos.api.NetworkApi;
import com.example.ilijaangeleski.gitrepos.manager.RepositoriesManager;
import com.example.ilijaangeleski.gitrepos.presenter.RepositoriesPresenter;
import com.example.ilijaangeleski.gitrepos.view.RepositoriesView;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */
@Module
public class RepositoriesActivityModule {
    WeakReference<RepositoriesView> view;

    public RepositoriesActivityModule(RepositoriesView view){
        this.view = new WeakReference<>(view);
    }

    @Provides
    RepositoriesPresenter providesRepositoriesPresenter(RepositoriesManager repositoriesManager){
        return new RepositoriesPresenter(view,repositoriesManager);
    }

    @Provides
    RepositoriesManager providesRepositoriesManager(NetworkApi networkApi){
        return new RepositoriesManager(networkApi);
    }
}
