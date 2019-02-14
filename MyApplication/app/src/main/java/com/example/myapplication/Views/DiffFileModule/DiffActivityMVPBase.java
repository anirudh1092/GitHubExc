package com.example.myapplication.Views.DiffFileModule;

import com.example.myapplication.API.Models.UserCommitDiffs;

import io.reactivex.Observable;


public interface DiffActivityMVPBase {


    public interface DiffModel{
        public Observable<UserCommitDiffs> getDiffs(String userName, String repoName, String sha);
    }


    public interface DiffView{
        public void updateData(UserCommitDiffs data);

    }
    public interface DiffPresenter{
        public void loadData();
        public void unsubscribeRx();
        public void setView(DiffActivityMVPBase.DiffView view);
    }
}
