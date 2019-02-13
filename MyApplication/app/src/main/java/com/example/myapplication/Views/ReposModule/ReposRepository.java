package com.example.myapplication.Views.ReposModule;


import com.example.myapplication.API.Models.GitHubRepos;

import java.util.List;

import io.reactivex.Observable;

public interface ReposRepository {



    public Observable<List<GitHubRepos>> getDataFromNetwork(String userNames);


    public Observable<List<GitHubRepos>> getResults(String userName);

}
