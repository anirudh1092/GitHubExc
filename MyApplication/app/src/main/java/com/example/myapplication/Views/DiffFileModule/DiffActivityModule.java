package com.example.myapplication.Views.DiffFileModule;

import com.example.myapplication.API.GitHubService;

import dagger.Module;
import dagger.Provides;

@Module
public class DiffActivityModule {

    @Provides
    public DiffActivityMVPBase.DiffPresenter providesPresenter(DiffActivityMVPBase.DiffModel model){
        return new DiffActivityPresenter(model);
    }

    @Provides
    public DiffActivityMVPBase.DiffModel providesModel(DiffsRepository repository){
        return new DiffActivityModel(repository);
    }

    @Provides
    public DiffsRepository providesRepository(GitHubService service){
        return  new DiffActivityRepository(service);
    }


}
