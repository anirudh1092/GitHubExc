package com.example.myapplication.Views.DiffFileModule;

import com.example.myapplication.API.GitHubService;

import dagger.Module;
import dagger.Provides;

@Module
public class DiffFilesActivityModule {

    @Provides
    public DiffFilesActivityMVPBase.DiffPresenter providesPresenter(DiffFilesActivityMVPBase.DiffModel model){
        return new DiffFilesActivityPresenter(model);
    }

    @Provides
    public DiffFilesActivityMVPBase.DiffModel providesModel(DiffsFilesRepository repository){
        return new DiffFilesActivityModel(repository);
    }

    @Provides
    public DiffsFilesRepository providesRepository(GitHubService service){
        return  new DiffActivityFilesRepository(service);
    }


}
