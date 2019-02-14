package com.example.myapplication.Views.CommitsModule;

import com.example.myapplication.API.GitHubService;

import dagger.Module;
import dagger.Provides;

@Module
public class CommitsActivityModule  {

    @Provides
    public CommitsActivityMVPBase.CommitsPresenter providesCommitsActivityPresenter(CommitsActivityMVPBase.CommitsModel model){
        return new CommitsActivityPresenter(model);
    }


    @Provides
    public CommitsActivityMVPBase.CommitsModel providesCommitsActivityModel(CommitsRepository commitsRepository){
        return new CommitsActivityModel(commitsRepository);
    }


    @Provides
    public CommitsRepository providesCommitRepository(GitHubService service){
        return new CommitsActivityRepositorty(service);
    }
}
