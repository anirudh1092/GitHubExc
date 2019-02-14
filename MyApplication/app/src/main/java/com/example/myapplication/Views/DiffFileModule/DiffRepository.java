package com.example.myapplication.Views.DiffFileModule;

import com.example.myapplication.API.GitHubService;
import com.example.myapplication.API.Models.UserCommitDiffs;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class DiffRepository implements DiffsRepository {

    private GitHubService service;
    List<UserCommitDiffs> diffsList;
    public DiffRepository(GitHubService service) {
        this.service = service;
        diffsList=new ArrayList<>();
    }

    @Override
    public Observable<UserCommitDiffs> getUserDiffsFromNetwork(String userName, String repoName, String sha) {
        Observable<UserCommitDiffs>observable=service.getDiffs(userName,repoName,sha).doOnNext(new Consumer<UserCommitDiffs>() {
            @Override
            public void accept(UserCommitDiffs userCommitDiffs) throws Exception {
                diffsList.add(userCommitDiffs);
            }
        });
        return observable;
    }

    @Override
    public Observable<UserCommitDiffs> getUserDiffs(String userName, String repoName, String sha) {
        return getUserDiffsFromNetwork(userName,repoName,sha);
    }
}
