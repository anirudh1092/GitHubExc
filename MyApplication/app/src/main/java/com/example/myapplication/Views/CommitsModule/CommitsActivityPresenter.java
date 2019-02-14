package com.example.myapplication.Views.CommitsModule;

import android.util.Log;

import com.example.myapplication.API.Models.GitHubRepos;
import com.example.myapplication.API.Models.UserCommits;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CommitsActivityPresenter implements CommitsActivityMVPBase.CommitsPresenter {

    public final String TAG=getClass().getName();
    CommitsActivityMVPBase.CommitsModel model;
    CommitsActivityMVPBase.CommitsView view;
    Disposable subscription=null;

    public CommitsActivityPresenter(CommitsActivityMVPBase.CommitsModel model){
        this.model=model;
    }

    @Override
    public void loadData() {
        subscription=model
                .getUserCommits("anirudh1092","GitHubDemo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<UserCommits>>() {
                    @Override
                    public void onNext(List<UserCommits> userCommits) {

                        for(int i=0;i<userCommits.size();i++){
                            view.upadateData(userCommits.get(i));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    @Override
    public void unsubscribeRx() {
        subscription.dispose();
    }

    @Override
    public void setView(CommitsActivityMVPBase.CommitsView view) {
        this.view=view;
    }
}
