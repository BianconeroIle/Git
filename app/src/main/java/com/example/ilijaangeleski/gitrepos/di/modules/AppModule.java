package com.example.ilijaangeleski.gitrepos.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */
@Module
public class AppModule {
    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext(){
        return context;
    }
}
