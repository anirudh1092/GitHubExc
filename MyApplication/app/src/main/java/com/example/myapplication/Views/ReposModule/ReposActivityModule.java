package com.example.myapplication.Views.ReposModule;


import com.example.myapplication.API.GitHubService;

import dagger.Module;
import dagger.Provides;

@Module
public class ReposActivityModule {

    @Provides
    public ReposActivityMVPBase.Presenter providesReposActivityPresenter(ReposActivityMVPBase.Model model){
        return new ReposActivityPresenter(model);
    }


    @Provides
    public ReposActivityMVPBase.Model providesReposActivityModel(ReposRepository reposRepository){
        return new ReposActivityModel(reposRepository);
    }



    @Provides
     public ReposRepository providesRepository(GitHubService service){
        return new ReposActivityRepository(service);
    }
}
