package com.example.myapplication.Views.CommitsModule;


import com.example.myapplication.API.GitHubService;
import com.example.myapplication.API.Models.UserCommits;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class CommitsActivityRepositorty implements  CommitsRepository {


    private GitHubService service;
    List<UserCommits>userCommitsList;

    public CommitsActivityRepositorty(GitHubService service) {
        this.service = service;
        userCommitsList=new ArrayList<>();
    }


    @Override
    public Observable<List<UserCommits>> getDataFromNetwork(String userName, String selectedRepoName) {

        Observable<List<UserCommits>> observable=service.getRepoCommits(userName,selectedRepoName).doOnNext(new Consumer<List<UserCommits>>() {
            @Override
            public void accept(List<UserCommits> userCommits) throws Exception {
                for(int i=0;i<userCommits.size();i++){
                    userCommitsList.add(userCommits.get(i));
                }
            }
        });
        return observable;
    }

    //ToDo:Need to implement Switch between the Network call and Memory for accesing the data


    @Override
    public Observable<List<UserCommits>> getCommitsData(String userName, String selectedRepoName) {
        return getDataFromNetwork(userName,selectedRepoName);
    }
}
