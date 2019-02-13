package com.example.myapplication.RootComponents;

import android.app.Application;

import com.example.myapplication.API.Models.GitHubClientService;

public class App extends Application {


    private ApplicationComponent  component;

    @Override
    public void onCreate() {
        super.onCreate();

        component=DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .gitHubClientService(new GitHubClientService())
                .build();

    }

    public ApplicationComponent getComponent(){
        return component;
    }
}

