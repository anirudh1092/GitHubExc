package com.example.myapplication.Views.ReposModule;

import com.example.myapplication.API.Models.GitHubRepos;
import com.example.myapplication.Views.CommitsModule.CommitsActivityMVPBase;

import java.util.List;

import io.reactivex.Observable;

public interface ReposActivityMVPBase {


    public interface Model{

        public Observable<List<GitHubRepos>> getRepos(String userName);

    }


    public interface View{

        public void updateData(GitHubRepos data);

        public void showProgressBar();

        public void hideProgressBar();
        public void showToastMessage(String error);
    }


    public interface Presenter{

        public void loadData(String userName);

        public void unsubscribeRX();


        void setView(ReposActivityMVPBase.View view);


    }
}
