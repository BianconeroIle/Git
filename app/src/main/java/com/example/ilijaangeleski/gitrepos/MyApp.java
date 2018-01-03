package com.example.ilijaangeleski.gitrepos;

import android.app.Application;

import com.example.ilijaangeleski.gitrepos.di.components.DaggerBaseComponent;
import com.example.ilijaangeleski.gitrepos.di.modules.AppModule;
import com.example.ilijaangeleski.gitrepos.di.components.BaseComponent;
import com.example.ilijaangeleski.gitrepos.di.modules.NetworkModule;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */

public class MyApp extends Application {
    private BaseComponent baseComponent;
    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        baseComponent = DaggerBaseComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public BaseComponent getBaseComponent() {
        return baseComponent;
    }

    public static MyApp getApp() {
        return app;
    }
}
