package com.example.myapplication.Views.DiffFileModule;


import com.example.myapplication.API.Models.UserCommitDiffs;

import io.reactivex.Observable;

public interface DiffsRepository {


    //TODo : Implement Methods which can be used to check if the data is in memory and cacahe accordingly

   // public Observable<List<UserCommitDiffs>> getUserDiffsFromMemory();


    public Observable<UserCommitDiffs> getUserDiffsFromNetwork(String userName,String repoName, String sha);


    public Observable<UserCommitDiffs> getUserDiffs(String userName,String repoName,String sha);

}
