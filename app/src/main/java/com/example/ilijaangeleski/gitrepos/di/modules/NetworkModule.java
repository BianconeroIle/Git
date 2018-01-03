package com.example.ilijaangeleski.gitrepos.di.modules;

import com.example.ilijaangeleski.gitrepos.api.NetworkApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */
@Module
public class NetworkModule {

    @Provides
    NetworkApi provideNetworkApi(Retrofit retrofit){
        return retrofit.create(NetworkApi.class);
    }

    @Provides
    Retrofit providesRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
