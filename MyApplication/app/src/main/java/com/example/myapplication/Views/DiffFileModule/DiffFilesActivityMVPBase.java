package com.example.myapplication.Views.DiffFileModule;

import com.example.myapplication.API.Models.UserCommitDiffs;
import com.example.myapplication.API.Models.UserCommitFiles;

import io.reactivex.Observable;


public interface DiffFilesActivityMVPBase {


    public interface DiffModel{
        public Observable<UserCommitDiffs> getDiffs(String userName, String repoName, String sha);
    }


    public interface DiffView{
        public void updateData(UserCommitFiles data);

    }
    public interface DiffPresenter{
        public void loadData();
        public void unsubscribeRx();
        public void setView(DiffFilesActivityMVPBase.DiffView view);
    }
}
