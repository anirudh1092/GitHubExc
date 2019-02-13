package com.example.myapplication.Views.ReposModule;


import android.util.Log;

import com.example.myapplication.API.Models.GitHubRepos;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ReposActivityPresenter implements  ReposActivityMVPBase.Presenter{

    private final String TAG=getClass().getName();
    ReposActivityMVPBase.Model model;
    private Disposable subscription=null;
    private ReposActivityMVPBase.View view;;
    public ReposActivityPresenter(ReposActivityMVPBase.Model model) {
        this.model = model;
    }


    @Override
    public void loadData() {
        String userName="anirudh1092";
       subscription= model.getRepos(userName)
                .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(new DisposableObserver<List<GitHubRepos>>() {
                     @Override
                     public void onNext(List<GitHubRepos> gitHubRepos) {
                         for(int i=0;i<gitHubRepos.size();i++)
                            view.updateData(gitHubRepos.get(i));
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
    public void unsubscribeRX() {
        subscription.dispose();
    }

    @Override
    public void setView(ReposActivityMVPBase.View view) {
            this.view=view;
    }
}
