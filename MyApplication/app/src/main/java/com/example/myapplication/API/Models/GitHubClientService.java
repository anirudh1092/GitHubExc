package com.example.myapplication.API.Models;

import com.example.myapplication.API.GitHubService;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class GitHubClientService {

    private final String base_url="https://api.github.com";


    @Provides
    public Retrofit provideRetrofit(String base_url){
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
     public GitHubService providesApiService(){
        return provideRetrofit(base_url).create(GitHubService.class);
    }
}
