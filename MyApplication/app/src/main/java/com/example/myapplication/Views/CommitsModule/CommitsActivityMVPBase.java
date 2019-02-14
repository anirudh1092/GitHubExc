package com.example.myapplication.Views.CommitsModule;

import com.example.myapplication.API.Models.UserCommitDiffs;
import com.example.myapplication.API.Models.UserCommits;

import java.util.List;

import io.reactivex.Observable;


public interface CommitsActivityMVPBase {

    interface CommitsModel{

            public Observable<List<UserCommits>>getUserCommits(String userName,String selectedRepo);

    }

    interface CommitsPresenter{

        public void loadData();
        public void unsubscribeRx();
        public void setView(CommitsActivityMVPBase.CommitsView view);

    }

    interface CommitsView{

        public void upadateData(UserCommits commit);
    }

}
