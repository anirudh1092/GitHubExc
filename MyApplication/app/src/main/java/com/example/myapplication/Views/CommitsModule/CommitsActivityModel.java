package com.example.myapplication.Views.CommitsModule;

import com.example.myapplication.API.Models.UserCommits;

import java.util.List;

import io.reactivex.Observable;

public class CommitsActivityModel implements  CommitsActivityMVPBase.CommitsModel {

    private CommitsRepository repository;

    public CommitsActivityModel(CommitsRepository repository) {
        this.repository = repository;
    }


    @Override
    public Observable<List<UserCommits>> getUserCommits(String userName, String selectedRepo) {
        return repository.getCommitsData(userName,selectedRepo);

    }
}
