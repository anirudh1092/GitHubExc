package com.example.myapplication.RootComponents;


import com.example.myapplication.API.Models.GitHubClientService;
import com.example.myapplication.Views.CommitsModule.CommitsActivity;
import com.example.myapplication.Views.CommitsModule.CommitsActivityModule;
import com.example.myapplication.Views.ReposModule.ReposActivity;
import com.example.myapplication.Views.ReposModule.ReposActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {GitHubClientService.class,ApplicationModule.class, ReposActivityModule.class,CommitsActivityModule.class})

public interface ApplicationComponent  {



     void inject(ReposActivity target);

     void injectCommitsActivity(CommitsActivity target);
}
