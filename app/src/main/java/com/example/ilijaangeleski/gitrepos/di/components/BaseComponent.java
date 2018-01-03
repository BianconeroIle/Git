package com.example.ilijaangeleski.gitrepos.di.components;

import android.content.Context;

import com.example.ilijaangeleski.gitrepos.api.NetworkApi;
import com.example.ilijaangeleski.gitrepos.di.modules.AppModule;
import com.example.ilijaangeleski.gitrepos.di.modules.NetworkModule;

import dagger.Component;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */
@Component(modules = {NetworkModule.class, AppModule.class})
public interface BaseComponent {
    Context getContext();

    NetworkApi getNetworkApi();
}
