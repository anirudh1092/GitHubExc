package com.example.myapplication.Views.DiffFileModule;

import com.example.myapplication.API.Models.UserCommitDiffs;

import io.reactivex.Observable;

public class DiffFilesActivityModel implements  DiffFilesActivityMVPBase.DiffModel {

    private DiffsFilesRepository repository;

    public DiffFilesActivityModel(DiffsFilesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<UserCommitDiffs> getDiffs(String userName, String repoName, String sha) {
        return repository.getUserDiffs(userName,repoName,sha);
    }
}
