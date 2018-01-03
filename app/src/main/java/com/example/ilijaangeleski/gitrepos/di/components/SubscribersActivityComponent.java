package com.example.ilijaangeleski.gitrepos.di.components;

import com.example.ilijaangeleski.gitrepos.di.modules.SubscribersDetailsActivityModule;
import com.example.ilijaangeleski.gitrepos.ui.RepositoriesDetailsActivity;

import dagger.Component;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */
@Component(modules = SubscribersDetailsActivityModule.class,dependencies = BaseComponent.class)
public interface SubscribersActivityComponent {
    void inject(RepositoriesDetailsActivity activity);
}
