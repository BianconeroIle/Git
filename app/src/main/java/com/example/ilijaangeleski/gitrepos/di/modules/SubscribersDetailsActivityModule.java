package com.example.ilijaangeleski.gitrepos.di.modules;

import com.example.ilijaangeleski.gitrepos.api.NetworkApi;
import com.example.ilijaangeleski.gitrepos.manager.SubscribersRepositoriesManager;
import com.example.ilijaangeleski.gitrepos.presenter.SubscribersRepositoriesPresenter;
import com.example.ilijaangeleski.gitrepos.view.SubscribersView;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */
@Module
public class SubscribersDetailsActivityModule {
    WeakReference<SubscribersView> view;

    public SubscribersDetailsActivityModule(SubscribersView view) {
        this.view = new WeakReference<>(view);
    }
    @Provides
    SubscribersRepositoriesPresenter providesSubscribersRepositoriesPresenter(SubscribersRepositoriesManager subscribersRepositoriesManager){
        return new SubscribersRepositoriesPresenter(view,subscribersRepositoriesManager);
    }

    @Provides
    SubscribersRepositoriesManager providesSubscribersRepositoriesManager(NetworkApi networkApi){
        return new SubscribersRepositoriesManager(networkApi);
    }
}
