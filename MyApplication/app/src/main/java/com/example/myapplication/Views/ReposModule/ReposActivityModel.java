package com.example.myapplication.Views.ReposModule;

import com.example.myapplication.API.Models.GitHubRepos;

import java.util.List;

import io.reactivex.Observable;

public class ReposActivityModel implements  ReposActivityMVPBase.Model {

    ReposRepository reposRepository;

    public ReposActivityModel(ReposRepository reposRepository) {
        this.reposRepository = reposRepository;
    }


    @Override
    public Observable<List<GitHubRepos>> getRepos(String userName) {
       return reposRepository.getResults(userName);

    }
}
