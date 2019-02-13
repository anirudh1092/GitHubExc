package com.example.myapplication.Views.CommitsModule;

import com.example.myapplication.API.Models.UserCommits;

import java.util.List;

import io.reactivex.Observable;

public interface CommitsRepository {

    public  Observable<List<UserCommits>> getDataFromNetwork(String userName,String selectedRepo);

    public Observable<List<UserCommits>>getCommitsData(String userName,String selectedRepo);


}
