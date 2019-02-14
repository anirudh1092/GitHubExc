package com.example.myapplication.Views.DiffFileModule;

import com.example.myapplication.API.Models.UserCommitDiffs;

import io.reactivex.Observable;

public class DiffActivityModel implements  DiffActivityMVPBase.DiffModel {

    private DiffsRepository repository;

    public DiffActivityModel(DiffsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<UserCommitDiffs> getDiffs(String userName, String repoName, String sha) {
        return repository.getUserDiffs(userName,repoName,sha);
    }
}
