package com.example.myapplication.Views.ReposModule;

import com.example.myapplication.API.GitHubService;
import com.example.myapplication.API.Models.GitHubRepos;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ReposActivityRepository implements ReposRepository {

    private GitHubService service;
    List<GitHubRepos> reposData;
    private  long timestamp;
    public ReposActivityRepository(GitHubService service) {
        this.service = service;
        reposData=new ArrayList<>();
        this.timestamp=System.currentTimeMillis();
    }

    private boolean isTimeExpired(){
        return System.currentTimeMillis() - timestamp < 20*1000;
    }



    @Override
    public Observable<List<GitHubRepos>> getDataFromNetwork(String userName) {

        Observable<List<GitHubRepos>>observable=service.getUserRepos(userName).doOnNext(new Consumer<List<GitHubRepos>>() {
            @Override
            public void accept(List<GitHubRepos> gitHubRepos) throws Exception {
                for(int i=0;i<gitHubRepos.size();i++)
                reposData.add(gitHubRepos.get(i));
            }
        });
       return observable;
    }




    public Observable<List<GitHubRepos>> getResults(String userName) {
          return   getDataFromNetwork(userName);
    }
}
