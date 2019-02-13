package com.example.myapplication.Views.ReposModule;

import com.example.myapplication.API.Models.GitHubRepos;

import java.util.List;

import io.reactivex.Observable;

public interface ReposActivityMVPBase {


    public interface Model{

        public Observable<List<GitHubRepos>> getRepos(String userName);

    }


    public interface View{

        public void updateData(GitHubRepos data);



    }


    public interface Presenter{

        public void loadData();

        public void unsubscribeRX();


        void setView(ReposActivityMVPBase.View view);


    }
}
