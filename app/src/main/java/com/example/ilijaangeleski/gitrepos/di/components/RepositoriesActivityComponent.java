package com.example.ilijaangeleski.gitrepos.di.components;

import com.example.ilijaangeleski.gitrepos.di.modules.RepositoriesActivityModule;
import com.example.ilijaangeleski.gitrepos.ui.RepositoriesActivity;

import dagger.Component;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */
@Component(modules = RepositoriesActivityModule.class,dependencies = BaseComponent.class)
public interface RepositoriesActivityComponent {
    void inject(RepositoriesActivity activity);
}
